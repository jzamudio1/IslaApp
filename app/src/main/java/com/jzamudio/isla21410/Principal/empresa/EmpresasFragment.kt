package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.adapter.PlayaAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentEmpresasBinding
import com.jzamudio.isla21410.databinding.FragmentInicioBinding
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import kotlinx.coroutines.launch

class EmpresasFragment : Fragment() {

    private var _binding: FragmentEmpresasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmpresasBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            binding.btnEmpresas.adapter = EmpresaInitAdapter(FirebaseBD().getlistEmpresaInit(),FirebaseBD().getlistEmpresas())
            binding.btnEmpresas.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}