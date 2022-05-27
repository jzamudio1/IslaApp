package com.jzamudio.isla21410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.ActivityMainBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickTurismo
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ClickTurismo.coleccionTurismo = "inicio"
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameTurismo()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView
         val navController = findNavController(R.id.nav_host_fragment_activity_main)


        navView.setupWithNavController(navController)


    }

    }
