package com.jzamudio.isla21410

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.ActivityMainBinding
import com.jzamudio.isla21410.login.AuthActivity
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.flagInvitado
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.flagLogin
import com.jzamudio.isla21410.util.ClickTurismo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isConected()
        isInvitado()
        loadTurismo()
        loadEmpresa()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)


    }

    fun isInvitado(){
        if (flagInvitado) {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
            flagInvitado = false
        }
    }

    fun isConected() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            val homeIntent = Intent(this, AuthActivity::class.java)
            startActivity(homeIntent)
        }

    }

    fun loadTurismo() {
        ClickTurismo.coleccionTurismo = "inicio"
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameTurismo()
        }
    }

    fun loadEmpresa() {
        ClickEmpresas.coleccionEmpresas = "empresas"
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameEmpresas()
        }
    }


    /**
     * Método ejecutado al pulsar el botón de back
     */
    override fun onBackPressed() {
        // Si el usuario se encuentra en la vista Login se minimiza la aplicación
        if (!flagLogin) moveTaskToBack(true)

        // En caso contrario se ejecuta la acción por defecto
        else super.onBackPressed()
    }


}
