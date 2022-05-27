package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentEmpresasBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import kotlinx.coroutines.launch

class EmpresasFragment : Fragment() {


    private var _binding: FragmentEmpresasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ClickEmpresas.coleccionEmpresas = "empresas"
        _binding = FragmentEmpresasBinding.inflate(inflater, container, false)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.btnEmpresas.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            binding.btnEmpresas.adapter = EmpresaInitAdapter(FirebaseBD().getlistSimpleNameEmpresas())
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_empresas_to_newEmpresaFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}