package com.jzamudio.isla21410.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.databinding.FragmentAuthBinding
import com.jzamudio.isla21410.util.ClickEmpresas


/**
 *Clase que Autentica a un usuario.
 */
class AuthFragment : Fragment() {
    //Binding
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)


        //Click Boton Login
        binding.login.setOnClickListener {
            setup()
        }
        //Click Boton Registrarse
        binding.register.setOnClickListener {
            showRegister()
        }


        return binding.root
    }


    /**
     * Toast Si la Validacion de los campos es erronea
     */
    private fun toast() {
        val text = "Debes Introducir campos correctos"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(requireContext(), text, duration)
        toast.show()
    }
    /**
     *Toast Si el inicio de sesion es erroneo
     */
    private fun toastError() {
        val text = "Correo y contraseñas incorrectas"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(requireContext(), text, duration)
        toast.show()
    }

    /**
     *Metodo que Compueba los campos y si es true, Inicia Sesion.
     */
    private fun setup() {
        if (validarForm()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        ClickEmpresas.flagLogin = false
                        showHome()
                    }
                }.addOnFailureListener { toastError() }
        } else
            toast()
    }

    /**
     *Navega al Inicio
     */
    private fun showHome() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToNavigationTurismo())
    }

    /**
     * Navega a Registrate
     */

    private fun showRegister() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment())
    }


    /**
     *Metodo que valida que los campos no estan vacios
     */
    private fun validarForm(): Boolean {
        var esValido = true

        if (TextUtils.isEmpty(binding.username.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.username.error = "Requerido"
            esValido = false
        } else binding.username.error = null


        if (TextUtils.isEmpty(binding.password.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.password.error = "Requerido"
            esValido = false
        } else binding.password.error = null

        return esValido
    }

}