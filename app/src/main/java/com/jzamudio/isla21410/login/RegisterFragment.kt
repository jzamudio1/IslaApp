package com.jzamudio.isla21410.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jzamudio.isla21410.databinding.FragmentRegisterBinding

/**
 * Clase que registra un usuario
 */

class RegisterFragment : Fragment() {
    //Binding
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    //Viewmodel
    private lateinit var viewModel: RegisterViewModel
    private lateinit var viewModelFactory: RegisterViewModel.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Paso por parametros los editText
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModelFactory = RegisterViewModel.Factory(
            binding.editTextCorreo.text,
            binding.editTextPassword.text,
            binding.editTextNombre.text,
            this
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]



        //Boton que Crea la cuenta
        binding.btnGuardar.setOnClickListener {
            viewModel.register()
        }

        //Boton para subir una foto
        binding.btnGaleria.setOnClickListener {
            viewModel.dispatchPickFromGalleryIntent()
        }

        changeIMG()

        // Inflate the layout for this fragment
        return binding.root
    }


    /**
     *Observa si se ha introducido una imagen
     */
   private fun changeIMG() {
        viewModel.changeIMG.observe(viewLifecycleOwner, Observer {
            binding.imgFoto.setImageURI(viewModel.URI.toUri())
        })
        viewModel._changeIMG.value = false
    }

    /**
     *Valida los Campos
     */

    fun validarForm(): Boolean {
        var esValido = true

        if (TextUtils.isEmpty(binding.editTextCorreo.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextCorreo.error = "Requerido"
            esValido = false
        } else binding.editTextCorreo.error = null

        if (TextUtils.isEmpty(binding.editTextPassword.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextPassword.error = "Requerido"
            esValido = false
        } else binding.editTextPassword.error = null


        if (TextUtils.isEmpty(binding.editTextNombre.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.editTextNombre.error = "Requerido"
            esValido = false
        } else binding.editTextNombre.error = null


        return esValido
    }

    /**
     *Navega al Login
     */
    fun showHome() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToAuthFragment())
    }


}