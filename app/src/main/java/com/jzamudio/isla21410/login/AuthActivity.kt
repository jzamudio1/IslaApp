package com.jzamudio.isla21410.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.MainActivity
import com.jzamudio.isla21410.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

       login.setOnClickListener {
           setup()
       }
        register.setOnClickListener {
            showRegister()
        }
    }

    private fun setup() {
        title = "Autentificacion"
            if(username.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome()
                    } else {

                    }
                }
            }
        }


    private fun showHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    private fun showRegister(){
        val registerIntent = Intent(this, registerActivity::class.java)
        startActivity(registerIntent)
    }
}