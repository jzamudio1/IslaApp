package com.jzamudio.isla21410.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.MainActivity
import com.jzamudio.isla21410.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        onLogin()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login.setOnClickListener {
            setup()

        }
        register.setOnClickListener {
            showRegister()
        }


        txtInvitado.setOnClickListener {
            showHome()
        }
    }




    private fun toast() {
        val text = "Debes Iniciar Sesion"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun setup() {
        if (validarForm()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome()
                    }
                }
        } else
            toast()
    }


    private fun showHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    private fun showRegister() {
        val registerIntent = Intent(this, registerActivity::class.java)
        startActivity(registerIntent)
    }


    private fun validarForm(): Boolean {
        var esValido = true
        val min = 4

        if (TextUtils.isEmpty(username.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            username.error = "Requerido"
            esValido = false
        } else username.error = null

        if (username.text.toString() < min.toString()) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            username.error = "Minimo 5 Caracteres"
            esValido = false
        } else username.error = null

        if (TextUtils.isEmpty(password.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            password.error = "Requerido"
            esValido = false
        } else password.error = null

        return esValido
    }



   fun  onLogin(){
        if(FirebaseAuth.getInstance().currentUser != null)
        {
            showHome()
        }
    }
}