package com.jzamudio.isla21410.Principal.empresa

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.adapter.PatrimonioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import kotlinx.coroutines.launch

class listEmpresaFragment : Fragment() {


    private var _binding: FragmentListEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var empresaVM: listEmpresaFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListEmpresaBinding.inflate(inflater, container, false)

        //val args = listEmpresaFragmentArgs.fromBundle(requireArguments())


        select()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empresaVM = ViewModelProvider(this).get(listEmpresaFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }


    fun select() {
        when (ClickEmpresas.tipo) {
            ClickEmpresas.abogado ->
                lifecycleScope.launch {

                }
            ClickEmpresas.autoescuela ->
                lifecycleScope.launch {
                    binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistAutoescuelas())

                    binding.btnEmpresas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        }

            ClickEmpresas.alimentacion ->
                lifecycleScope.launch {
                    binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistAlimentacion())

                    binding.btnEmpresas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        }

            ClickEmpresas.farmacias ->
                lifecycleScope.launch {

                        }



            ClickEmpresas.ferreterias ->
                lifecycleScope.launch {


                }
            ClickEmpresas.inmobiliarias ->
                lifecycleScope.launch {

                }
            ClickEmpresas.manodeObra ->
                lifecycleScope.launch {


                }
            ClickEmpresas.moda ->
                lifecycleScope.launch {


                }
            ClickEmpresas.peluquerias ->
                lifecycleScope.launch {

                }
            ClickEmpresas.restauracion ->
                lifecycleScope.launch {
                    binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistRestaurantes())

                    binding.btnEmpresas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                }
            ClickEmpresas.talleres ->
                lifecycleScope.launch {


                }
            ClickEmpresas.taxis ->
                lifecycleScope.launch {

                }
            ClickEmpresas.veterinario ->
                lifecycleScope.launch {

                }
        }
    }

}