package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.Principal.turismo.InicioViewModel
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import kotlinx.coroutines.launch
/**
 * Clase que obtiene la lista de items de las categorias
 */
class listEmpresaFragment : Fragment() {

    private var _binding: FragmentListEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListEmpresaViewModel
    val listInit = mutableListOf<ComentUser>()
    private lateinit var adaptador: EmpresasAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListEmpresaBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ListEmpresaViewModel::class.java]


        onAdapter()


        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     *Metodo que Carga el adapter desde el viewModel
     */
    private fun onAdapter() {
        binding.btnEmpresas.adapter = viewModel.adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.btnEmpresas.layoutManager = linearLayoutManager

    }
}