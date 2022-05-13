package com.jzamudio.isla21410.Principal.empresa

import android.graphics.Path
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jzamudio.isla21410.databinding.FragmentEmpresasBinding
import com.jzamudio.isla21410.util.ClickListner

class EmpresasFragment : Fragment() {


    private var _binding: FragmentEmpresasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmpresasBinding.inflate(inflater, container, false)


        binding.btnAlojamiento.setOnClickListener {
            ClickListner.Alojamiento = true
            findNavController().navigate(EmpresasFragmentDirections.actionNavigationEmpresasToListEmpresaFragment())
        }


        binding.btnServicios.setOnClickListener {
            ClickListner.Servicio = true
            findNavController().navigate(EmpresasFragmentDirections.actionNavigationEmpresasToListEmpresaFragment())
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}