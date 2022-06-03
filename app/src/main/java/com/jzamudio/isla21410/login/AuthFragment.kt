package com.jzamudio.isla21410.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.MainActivity
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.databinding.FragmentAuthBinding
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import com.jzamudio.isla21410.util.ClickEmpresas



class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)



        binding.login.setOnClickListener {
            setup()
        }
        binding.register.setOnClickListener {
            showRegister()
        }


        return binding.root
    }


    private fun toast() {
        val text = "Debes Iniciar Sesion"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(requireContext(), text, duration)
        toast.show()
    }

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
                        Log.i("flag", ClickEmpresas.flagLogin.toString())
                        showHome()
                    }
                }
        } else
            toast()
    }


    private fun showHome() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToNavigationTurismo())
    }

    private fun showRegister() {
        findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment())
    }


    private fun validarForm(): Boolean {
        var esValido = true
        val min = 4

        if (TextUtils.isEmpty(binding.username.text.toString())) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.username.error = "Requerido"
            esValido = false
        } else binding.username.error = null

        if (binding.username.text.toString() < min.toString()) {
            // Si la propiedad error tiene valor, se muestra el aviso.
            binding.username.error = "Minimo 5 Caracteres"
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