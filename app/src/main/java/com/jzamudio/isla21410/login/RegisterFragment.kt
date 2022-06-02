package com.jzamudio.isla21410.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.jzamudio.isla21410.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.io.IOException


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val File = 1
    private var FileUri: String? = null
    private var urlImage: Uri? = null
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        k()
        binding.btnGuardar.setOnClickListener {
            createAccount()
        }

        binding.btnGaleria.setOnClickListener {
            dispatchPickFromGalleryIntent()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun createAccount() {
        if (validarForm()) {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(
                binding.editTextCorreo.text.toString().trim(),
                binding.editTextPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                        uploadPhoto()
                    showHome()
                } else {

                    Toast.makeText(
                        requireContext(), "Authentication failed.",
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
        Log.d("Mensaje", "Se subió correctamente" + FileUri)
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos)
        file_name.putBytes(baos.toByteArray()).addOnSuccessListener {
            file_name.downloadUrl.addOnSuccessListener { uri ->
                urlImage = uri
                val userID = FirebaseAuth.getInstance().currentUser!!.uid
                FirebaseFirestore.getInstance().collection("users").document(userID).set(
                    hashMapOf(
                        "uid" to userID,
                        "nombre" to binding.editTextNombre.text.toString(),
                        "correo" to binding.editTextCorreo.text.toString(),
                        "foto" to urlImage

                    )
                )
            }
        }
    }



    private fun getRealPathFromURI(contentUri: Uri): String {
        val cursor: Cursor = requireContext().contentResolver.query(
            contentUri, arrayOf(MediaStore.Images.Media.DATA), null, null,
            null
        )!!
        return cursor.use {
            val i = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            it.getString(i)
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == File) {
            if (resultCode == AppCompatActivity.RESULT_OK) {

                FileUri = data!!.data
                try {
                    binding.imgFoto.setImageURI(FileUri)
                    uploadPhoto()

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    }*/


    private fun validarForm(): Boolean {
        var esValido = true
        val min = 5

        if (TextUtils.isEmpty(binding.editTextCorreo.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextCorreo.error = "Requerido"
            esValido = false
        } else binding.editTextCorreo.error = null

        if (TextUtils.isEmpty(binding.editTextPassword.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextPassword.error = "Requerido"
            esValido = false
        } else binding.editTextPassword.error = null


        if (TextUtils.isEmpty(binding.editTextNombre.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextNombre.error = "Requerido"
            esValido = false
        } else binding.editTextNombre.error = null


        return esValido
    }

    private fun showHome() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToAuthFragment())
    }

    private fun dispatchPickFromGalleryIntent() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),2022)
        if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/"
                resultLauncher.launch(this)
            }
        }

    }

    fun k(){
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    FileUri = getRealPathFromURI(result.data?.data!!)
                    binding.imgFoto.setImageURI(result.data?.data!!)
                }
            }
    }
}