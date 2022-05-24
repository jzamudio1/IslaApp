package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.adapter.RestauracionAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import com.jzamudio.isla21410.databinding.FragmentListSubCategoriasEmpresaBinding
import com.jzamudio.isla21410.databinding.ItemRestauracionAdapterBinding
import kotlinx.coroutines.launch


class listSubCategoriasEmpresaFragment : Fragment() {
    private var _binding: FragmentListSubCategoriasEmpresaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListSubCategoriasEmpresaBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
            binding.reciclerRestauracion.adapter = RestauracionAdapter(FirebaseBD().getlistSimpleName())
            binding.reciclerRestauracion.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}