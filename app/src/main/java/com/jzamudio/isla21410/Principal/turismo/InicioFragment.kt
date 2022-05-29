package com.jzamudio.isla21410.Principal.turismo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentInicioBinding
import com.jzamudio.isla21410.util.ClickTurismo
import kotlinx.coroutines.launch

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ClickTurismo.coleccionTurismo = "inicio"
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.btnInicio.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            binding.btnInicio.adapter = InicioAdapter(FirebaseBD().getlistSimpleNameTurismo())
        }
        // Inflate the layout for this fragment


        return binding.root
    }




}