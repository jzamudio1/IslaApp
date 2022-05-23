package com.jzamudio.isla21410.Principal.empresa

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope

import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.Empresa

import com.jzamudio.isla21410.databinding.FragmentNewEmpresaBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import kotlinx.coroutines.launch

class NewEmpresaFragment : Fragment(), LifecycleObserver {


    private var _binding: FragmentNewEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewEmpresaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEmpresaBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
//            binding.spinner.adapter = ArrayAdapter(
//                requireContext(),
//                android.R.layout.simple_spinner_item,
//                FirebaseBD().getlistEmpresaInit().map {
//                    it.nombre
//                })
            ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                FirebaseBD().getlistEmpresaInit().map {
                    it.nombre
                }
            ).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter

            }
            binding.spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val prueba = binding.spinner.getItemAtPosition(p2).toString()
                    Log.i("pruebaaaaaaa", "${prueba}")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }


        binding.btnGuardarEmpresa.setOnClickListener {

            insertEmpresa()
        }


        // FirebaseBD().getlistEmpresaInit()[binding.spinner.selectedItemPosition]

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(this)
        viewModel = ViewModelProvider(this)[NewEmpresaViewModel::class.java]
    }

    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

    fun insertEmpresa() {

        val tipo = binding.spinner.selectedItem.toString()

        val empresa = Empresa(
            binding.etNombreEmpresa.text.toString(),
            binding.etTelefono.text.toString(),
            binding.etCorreo.text.toString(),
            binding.etPaginaWeb.text.toString(),
            binding.imgFotoEmpresa.toString(),
            binding.etDescripcion.text.toString(),
            binding.etDescripcion.text.toString(),

            )
        lifecycleScope.launch {
            FirebaseBD().insertEmpresa(tipo, empresa)
        }
    }


}


