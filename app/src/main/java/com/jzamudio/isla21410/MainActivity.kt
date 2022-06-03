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

/**
 * MainActivity
 */
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


    /**
     * Metodo que comprueba si hayn un usuario conectado, si es asi navega al Inicio, si no navega al fragmento de Login
     */
    @SuppressLint("ResourceType")
    fun isConected() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.authFragment)
        }

    }

    /**
     * Metodo que establece la navegacion de la app
     */
    private fun onNav() {
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView!!.setupWithNavController(navController)
    }

    /**
     * Metodo de carga inicial de los datos de turimo
     */
    private fun loadTurismo() {
        ClickTurismo.coleccionTurismo = "inicio"
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameTurismo()
        }
    }

    /**
     * Metodo de carga inicial de los datos de eempresa
     */
    private fun loadEmpresa() {
        ClickEmpresas.coleccionEmpresas = "empresas"
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameEmpresas()
        }
    }

    /**
     * Metodo que oculta el BottonNavigation en funcion de la ubicacion
     */
    private fun hideButton() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.authFragment -> hideBottomNav()
                R.layout.fragment_inicio -> showBottomNav()
                else -> showBottomNav()
            }
        }
    }

    /**
     *Metodo quye Muestra El bBotton Navigation
     */
    private fun showBottomNav() {
        navView!!.visibility = View.VISIBLE

    }

    /**
     *Metodo que Oculta el BottonNavigation
     */
    private fun hideBottomNav() {
        navView!!.visibility = View.GONE

    }


    /**
     * Método que comprueba la ubicacion, y si quiere navegar al login minimiza la app
     */
    override fun onBackPressed() {
        // Si el usuario se encuentra en la vista Login o Home se minimiza la aplicación
        if (navController.currentDestination?.id == R.id.authFragment
        ) moveTaskToBack(true)

        // En caso contrario se ejecuta la acción por defecto
        else super.onBackPressed()
    }


}
