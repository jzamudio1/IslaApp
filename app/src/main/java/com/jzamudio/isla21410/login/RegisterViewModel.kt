package com.jzamudio.isla21410.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jzamudio.isla21410.Principal.turismo.DetailTurismoViewModel
import com.jzamudio.isla21410.database.model.ComentUser
import java.io.ByteArrayOutputStream

class RegisterViewModel(val correo:Editable,val password:Editable, val nombre:Editable, val fragment: RegisterFragment) : ViewModel() {

    private val File = 1
    private var FileUri: String? = null
    private var urlImage: Uri? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    var URI:String = ""

     val _changeIMG = MutableLiveData<Boolean>()
    val changeIMG: LiveData<Boolean> get() = _changeIMG

    class Factory(
        val correo: Editable,
        val password: Editable,
        val nombre: Editable,
        val fragment: RegisterFragment
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegisterViewModel(correo,password,nombre, fragment) as T
        }
    }

    init {
        k()
    }


     fun createAccount() {
        if (fragment.validarForm()) {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(
                correo.toString().trim(),
               password.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    uploadPhoto()
                    fragment.showHome()
                } else {

                    Toast.makeText(
                        fragment.requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }



    fun uploadPhoto() {
        val Folder: StorageReference = FirebaseStorage.getInstance().reference.child("User")
        val file_name: StorageReference = Folder.child("file" + FileUri!!.split("/").last())
        val baos = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeFile(FileUri)
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos)
        file_name.putBytes(baos.toByteArray()).addOnSuccessListener {
            file_name.downloadUrl.addOnSuccessListener { uri ->
                urlImage = uri
                val userID = FirebaseAuth.getInstance().currentUser!!.uid
                FirebaseFirestore.getInstance().collection("users").document(userID).set(
                    hashMapOf(
                        "uid" to userID,
                        "nombre" to nombre.toString(),
                        "correo" to correo.toString(),
                        "foto" to urlImage

                    )
                )
            }
        }
    }



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



     fun dispatchPickFromGalleryIntent() {
        ActivityCompat.requestPermissions(fragment.requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),2022)
        if(ContextCompat.checkSelfPermission(fragment.requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/"
                resultLauncher.launch(this)
            }
        }

    }


    fun k(){
        resultLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    FileUri = getRealPathFromURI(result.data?.data!!)
                    URI = Uri.parse(result.data?.data!!.toString()).toString()
                    _changeIMG.value = true
                }
            }
    }
}