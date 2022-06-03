package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.FragmentNewEmpresaBinding

class NewEmpresaFragment : Fragment(), LifecycleObserver {


    private var _binding: FragmentNewEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewEmpresaViewModel
    private lateinit var viewModelFactory: NewEmpresaViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEmpresaBinding.inflate(inflater, container, false)
        viewModelFactory = NewEmpresaViewModel.Factory(
            binding.spinner,
            this,
            binding.etNombreEmpresa.text,
            binding.etCorreo.text,
            binding.etDireccion.text,
            binding.etDescripcion.text,
            binding.etPaginaWeb.text,
            binding.etTelefono.text
        )
        viewModel = ViewModelProvider(this,viewModelFactory)[NewEmpresaViewModel::class.java]


        changeIMG()

        binding.btnFoto.setOnClickListener {
            viewModel.dispatchPickFromGalleryIntent()
        }

        binding.btnGuardarEmpresa.setOnClickListener {
            viewModel.uploadPhoto()

        }



        return binding.root
    }


    fun changeIMG(){
        viewModel.changeIMG.observe(viewLifecycleOwner, Observer {
            binding.imgFotoEmpresa.setImageURI(viewModel.URI.toUri())
        })
        viewModel._changeIMG.value = false
    }



    fun validarForm(): Boolean {
        var esValido = true
        val min = 5

        if (TextUtils.isEmpty(binding.etNombreEmpresa.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.etNombreEmpresa.error = "Requerido"
            esValido = false
        } else binding.etNombreEmpresa.error = null

        if (binding.etNombreEmpresa.text.toString().trim() < min.toString()) {
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


