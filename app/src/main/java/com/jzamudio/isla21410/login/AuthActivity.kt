package com.jzamudio.isla21410.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.MainActivity
import com.jzamudio.isla21410.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setup()
    }

    private fun setup() {
        title = "Autentificacion"
        register.setOnClickListener {
            if(username.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(username.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome()
                    } else {

                    }
                }
            }
        }
        login.setOnClickListener {
            if(username.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome()
                    } else {

                    }
                }
            }
        }
    }

    private fun showHome(){
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}