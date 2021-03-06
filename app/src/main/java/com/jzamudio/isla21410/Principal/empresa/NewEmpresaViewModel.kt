package com.jzamudio.isla21410.Principal.empresa

import android.Manifest
import android.R
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
/**
 * ViewModel que maneja NewEmpresa
 */
class NewEmpresaViewModel(
    val spinner: Spinner,
    val fragment: NewEmpresaFragment,
    val nombre: Editable,
    val correo: Editable,
    val direccion: Editable,
    val descripcion: Editable,
    val paginaweb: Editable,
    val telefono: Editable
) : ViewModel() {

    private var FileUri: String? = null
    private var urlImage: Uri? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
   var URI: String = ""
    val _changeIMG = MutableLiveData<Boolean>()
    val changeIMG: LiveData<Boolean> get() = _changeIMG

    private val _flagProgres = MutableLiveData<Boolean>()
    val flagProgres: LiveData<Boolean> get() = _flagProgres
    val _flagCarga = MutableLiveData<Boolean>()
    val flagCarga: LiveData<Boolean> get() = _flagCarga


    class Factory(
        val spinner: Spinner,
        val fragment: NewEmpresaFragment,
        val nombre: Editable,
        val correo: Editable,
        val direccion: Editable,
        val descripcion: Editable,
        val paginaweb: Editable,
        val telefono: Editable
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewEmpresaViewModel(
                spinner,
                fragment,
                nombre,
                correo,
                direccion,
                descripcion,
                paginaweb,
                telefono
            ) as T
        }
    }


    init {
        onSpinnerSelector()
        dispathOK()
    }


    /**
     * Metodo que carga en el spinner las categorias de la empresa donde vamos a querer insertar la nueva empresa.
     */
    private fun onSpinnerSelector() {
        viewModelScope.launch {
            ArrayAdapter(fragment.requireContext(),
                R.layout.simple_spinner_item,
                FirebaseBD().getlistSimpleNameEmpresas().map {
                    it.nombre
                }
            ).also { adapter ->

                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

            }
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val prueba = spinner.getItemAtPosition(p2).toString()

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }
    }

    /**
     * Metodo que Inserta la empresa en la base de datos
     */
    private fun insertEmpresa() {
        val tipo = spinner.selectedItem.toString()

            val empresa = ComentUser(
                FirebaseAuth.getInstance().currentUser!!.uid,
                correo.toString(),
                descripcion.toString(),
                direccion.toString(),
                urlImage.toString(),
                nombre.toString(),
                telefono.toString(),
                paginaweb.toString()

            )
            FirebaseBD().insertEmpresa(tipo, empresa)
            fragment.findNavController().navigate(NewEmpresaFragmentDirections.actionNewEmpresaFragmentToNavigationEmpresas())
            _flagCarga.value = false
        }



    /**
     * Metodo que valida los campos y sube la foto
     * Cuando la foto esta subida y obtiene la url inserta la empresa.
     */
    fun uploadPhoto() {
        if (fragment.validarForm()) {
            if (FileUri?.isNotEmpty() == true) {
                _flagProgres.value = true
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().reference.child("EmpresaUser")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.split("/").last())
                val baos = ByteArrayOutputStream()
                val bitmap = BitmapFactory.decodeFile(FileUri)
                bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos)
                file_name.putBytes(baos.toByteArray()).addOnSuccessListener {
                    file_name.downloadUrl.addOnSuccessListener { uri ->
                        urlImage = uri
                        insertEmpresa()
                        _flagProgres.value = false

                    }
                }
            } else {
                toast()
            }
        }
    }


    /**
     * Metodo que obtiene el Path real de la imagen
     */
    fun getRealPathFromURI(contentUri: Uri): String {
        val cursor: Cursor = fragment.requireContext().contentResolver.query(
            contentUri, arrayOf(MediaStore.Images.Media.DATA), null, null,
            null
        )!!
        return cursor.use {
            val i = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            it.getString(i)
        }
    }

    /**
     * Metodo que abre la galeria
     */

    fun dispatchPickFromGalleryIntent() {
        ActivityCompat.requestPermissions(
            fragment.requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2022
        )
        if (ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/"
                resultLauncher.launch(this)
            }
        }

    }


    /**
     * Metodo que transforma la la ruta en URI y cambia la bandera
     */
    fun dispathOK() {
        resultLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    FileUri = getRealPathFromURI(result.data?.data!!)
                    URI = Uri.parse(result.data?.data!!.toString()).toString()
                    _changeIMG.value = true
                }
            }
    }


    /**
     * Metodo que muestra un toast de validacion
     */
    private fun toast() {
        val text = "Debes Insertar una Foto"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(fragment.requireContext(), text, duration)
        toast.show()
    }
}