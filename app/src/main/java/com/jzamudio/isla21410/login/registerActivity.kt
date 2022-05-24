package com.jzamudio.isla21410.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.tasks.await

class registerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btnGuardar.setOnClickListener {
            createAccount()
            showLogin()
        }
    }


    private  fun createAccount() {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(editTextCorreo.text.toString(), editTextPassword.text.toString())
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
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
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
    private fun showLogin(){
        val authIntent = Intent(this, AuthActivity::class.java)
        startActivity(authIntent)
    }


}