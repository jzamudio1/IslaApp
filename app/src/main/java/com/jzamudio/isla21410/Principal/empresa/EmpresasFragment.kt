package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.Principal.turismo.InicioViewModel
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.FragmentEmpresasBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import kotlinx.coroutines.launch

class EmpresasFragment : Fragment() {


    private var _binding: FragmentEmpresasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EmpresaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ClickEmpresas.coleccionEmpresas = "empresas"
        _binding = FragmentEmpresasBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[EmpresaViewModel::class.java]

        onAdapter()
        binding.floatingActionButton.setOnClickListener {
            isConectado()

        }

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun onAdapter() {
        binding.btnEmpresas.adapter = viewModel.adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.btnEmpresas.layoutManager = linearLayoutManager
    }

    fun isConectado() {
        val usuario = FirebaseAuth.getInstance().currentUser?.uid
        Log.i("usuario", usuario.toString())
        if (usuario != null) {
            findNavController().navigate(R.id.action_navigation_empresas_to_newEmpresaFragment)
        } else {

        }
    }

}

