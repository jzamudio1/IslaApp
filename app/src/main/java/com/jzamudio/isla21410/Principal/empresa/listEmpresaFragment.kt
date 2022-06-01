package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import kotlinx.coroutines.launch

class listEmpresaFragment : Fragment() {

    private var _binding: FragmentListEmpresaBinding? = null
    private val binding get() = _binding!!
    val listInit = mutableListOf<ComentUser>()
    private lateinit var adaptador: EmpresasAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListEmpresaBinding.inflate(inflater, container, false)


        onAdapter()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onAdapter() {
        listInit.clear()
        adaptador = EmpresasAdapter(listInit)
        binding.btnEmpresas.adapter = adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.btnEmpresas.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            listInit.addAll(FirebaseBD().getDetailEmpresa())
            adaptador.notifyDataSetChanged()
        }
    }
}