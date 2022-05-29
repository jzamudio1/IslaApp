package com.jzamudio.isla21410.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jzamudio.isla21410.R
import kotlinx.android.synthetic.main.activity_register.*
import java.io.IOException


class registerActivity : AppCompatActivity() {
    private val File = 1
    private var FileUri: Uri? = null
    private var urlImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btnGuardar.setOnClickListener {
            createAccount()

        }

        btnGaleria.setOnClickListener {
            fileUploat()
        }

    }


    private fun createAccount() {
        if (validarForm()) {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(
                editTextCorreo.text.toString().trim(),
                editTextPassword.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userID = FirebaseAuth.getInstance().currentUser!!.uid
                        FirebaseFirestore.getInstance().collection("users").document(userID).set(
                            hashMapOf(
                                "uid" to userID,
                                "nombre" to editTextNombre.text.toString(),
                                "correo" to editTextCorreo.text.toString(),
                                "foto" to urlImage

                            )
                        )
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("login", "createUserWithEmail:success")
                        //updateProfile()
                    } else {
                        // If sign in fails, display a message to the user.to
                        Log.w("login", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


    private fun showLogin() {
        val authIntent = Intent(this, AuthActivity::class.java)
        startActivity(authIntent)
    }

    private fun fileUploat() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, File)
    }

    fun uploadPhoto() {
        val Folder: StorageReference =
            FirebaseStorage.getInstance().reference.child("User")
        val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
        file_name.putFile(FileUri!!).addOnSuccessListener {
            file_name.downloadUrl.addOnSuccessListener { uri ->
                val hashMap = java.lang.String.valueOf(uri)
                Firebase.database.getReference("User").setValue(hashMap)
                urlImage = uri
                Log.d("Mensaje", "Se subió correctamente, $urlImage")
            }


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == File) {
            if (resultCode == RESULT_OK) {
                FileUri = data!!.data
                try {
                    imgFoto.setImageURI(FileUri)
                    uploadPhoto()

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    }


    private fun validarForm(): Boolean {
        var esValido = true
        val min = 5

        if (TextUtils.isEmpty(editTextCorreo.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextCorreo.error = "Requerido"
            esValido = false
        } else editTextCorreo.error = null

        if (editTextCorreo.text.toString() < min.toString()) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextCorreo.error = "Minimo 5 Caracteres"
            esValido = false
        } else editTextCorreo.error = null

        if (Patterns.EMAIL_ADDRESS.matcher(editTextCorreo.text.toString().trim()).matches()){
            editTextCorreo.error = "Correo no valido"
            esValido = false
        } else editTextCorreo.error = null
        if (TextUtils.isEmpty(editTextPassword.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextPassword.error = "Requerido"
            esValido = false
        } else editTextPassword.error = null

        if (editTextPassword.text.toString() < min.toString()) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextPassword.error = "Minimo 5 Caracteres"
            esValido = false
        } else editTextPassword.error = null

        if (TextUtils.isEmpty(editTextNombre.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextNombre.error = "Requerido"
            esValido = false
        } else editTextNombre.error = null

        if (editTextNombre.text.toString() < min.toString()) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            editTextNombre.error = "Minimo 5 Caracteres"
            esValido = false
        } else editTextNombre.error = null
        return esValido
    }

}