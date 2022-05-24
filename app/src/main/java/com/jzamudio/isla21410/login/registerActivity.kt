package com.jzamudio.isla21410.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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


class registerActivity : AppCompatActivity() {
    private val File = 1
    private val database = Firebase.database
    val myRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btnGuardar.setOnClickListener {
            createAccount()
            showLogin()
        }

        btnGaleria.setOnClickListener {
            fileUploat()
        }

        imgFoto.setOnClickListener {

        }
    }


    private fun fileUploat() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, File)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == File) {
            if (resultCode == RESULT_OK) {
                val FileUri = data!!.data
                val Folder: StorageReference =
                    FirebaseStorage.getInstance().getReference().child("User")
                val file_name: StorageReference = Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.getDownloadUrl().addOnSuccessListener { uri ->
                        val hashMap =
                            HashMap<String, String>()
                        hashMap["link"] = java.lang.String.valueOf(uri)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje", "Se subió correctamente")
                    }
                }
            }
        }
    }
    
    private fun createAccount() {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(
            editTextCorreo.text.toString(),
            editTextPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userID = FirebaseAuth.getInstance().currentUser!!.uid
                    FirebaseFirestore.getInstance().collection("users").document("$userID").set(
                        hashMapOf(
                            "uid" to userID,
                            "nombre" to editTextNombre.text.toString(),
                            "correo" to editTextCorreo.text.toString()


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

    /*
        private fun updateProfile() {
            val user = FirebaseAuth.getInstance().currentUser
            val profileUpdates = userProfileChangeRequest {
                setDisplayName(editTextNombre.text.toString())
                //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
            }
            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "User profile updated.")
                    }

                }
        }
    */
    private fun showLogin() {
        val authIntent = Intent(this, AuthActivity::class.java)
        startActivity(authIntent)
    }


}