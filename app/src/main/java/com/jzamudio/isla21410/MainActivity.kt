package com.jzamudio.isla21410

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.ActivityMainBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickTurismo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var navView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNav()
        isConected()
        hideButton()
        loadTurismo()
        loadEmpresa()
    }


    @SuppressLint("ResourceType")
    fun isConected() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.authFragment)
        }

    }


    fun onNav() {
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView!!.setupWithNavController(navController)
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


    fun hideButton(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authFragment -> hideBottomNav()
                R.layout.fragment_inicio -> showBottomNav()
                else -> showBottomNav()
            }
        }
    }


    private fun showBottomNav() {
        navView!!.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        navView!!.visibility = View.GONE

    }

    /**
     * Método ejecutado al pulsar el botón de back
     */
    /**
     * Método ejecutado al pulsar el botón de back
     */
    override fun onBackPressed() {
        // Si el usuario se encuentra en la vista Login o Home se minimiza la aplicación
        if (navController.currentDestination?.id == R.id.authFragment
        ) moveTaskToBack(true)

        // En caso contrario se ejecuta la acción por defecto
        else super.onBackPressed()
    }


}
