package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.text.TextUtils
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
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.coroutines.launch

class NewEmpresaFragment : Fragment(), LifecycleObserver {


    private var _binding: FragmentNewEmpresaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEmpresaBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                FirebaseBD().getlistSimpleNameEmpresas().map {
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

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }


        binding.btnGuardarEmpresa.setOnClickListener {

            insertEmpresa()
        }



        return binding.root
    }


    override fun onDetach() {
        super.onDetach()
        lifecycle.removeObserver(this)
    }

    fun insertEmpresa() {
        val tipo = binding.spinner.selectedItem.toString()
        if (validarForm()) {
            val empresa = Empresa(
                binding.etNombreEmpresa.text.toString(),
                binding.etTelefono.text.toString(),
                binding.etCorreo.text.toString(),
                binding.etPaginaWeb.text.toString(),
                binding.imgFotoEmpresa.toString(),
                binding.etDescripcion.text.toString(),
                binding.etDireccion.text.toString(),
            )
            FirebaseBD().insertEmpresa(tipo, empresa)
        }


    }




    private fun validarForm(): Boolean {
        var esValido = true
       val min = 5

        if (TextUtils.isEmpty(binding.etNombreEmpresa.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.etNombreEmpresa.error = "Requerido"
            esValido = false
        } else binding.etNombreEmpresa.error = null

        if(binding.etNombreEmpresa.text.toString().trim() < min.toString()){
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.etNombreEmpresa.error = "Caracteres Minimo 5"
            esValido = false
        } else binding.etNombreEmpresa.error = null

        if (TextUtils.isEmpty(binding.etTelefono.text.toString())) {
            binding.etTelefono.error = "Requerido"
            esValido = false
        } else binding.etTelefono.error = null

        if (binding.etTelefono.text.toString() < min.toString()) {
            binding.etTelefono.error = "Caracteres Minimo 5"
            esValido = false
        } else binding.etTelefono.error = null

        if (TextUtils.isEmpty(binding.etCorreo.text.toString())) {
            binding.etCorreo.error = "Requerido"
            esValido = false
        } else binding.etCorreo.error = null

        if (binding.etCorreo.text.toString() < min.toString()) {
            binding.etCorreo.error = "Caracteres Minimo 5"
            esValido = false
        } else binding.etCorreo.error = null

        if (TextUtils.isEmpty(binding.etDescripcion.text.toString())) {
            binding.etDescripcion.error = "Requerido"
            esValido = false
        } else binding.etDescripcion.error = null
        if (binding.etDescripcion.text.toString() < min.toString()) {
            binding.etDescripcion.error = "Caracteres Minimo 5"
            esValido = false
        } else binding.etDescripcion.error = null
        if (TextUtils.isEmpty(binding.etDireccion.text.toString())) {
            binding.etDireccion.error = "Requerido"
            esValido = false
        } else binding.etDireccion.error = null
        if (binding.etDireccion.text.toString() < min.toString()) {
            binding.etDireccion.error = "Caracteres Minimo 5"
            esValido = false
        } else binding.etDireccion.error = null

        return esValido
    }


}


