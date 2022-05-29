package com.jzamudio.isla21410.Principal.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentConfigurateBinding
import kotlinx.coroutines.launch


class ConfigurateFragment : Fragment() {

    private var _binding: FragmentConfigurateBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuario:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentConfigurateBinding.inflate(inflater, container, false)



        binding.btnCerrarSesion.setOnClickListener {
            sinOut()
        }

            user()
        return  binding.root
    }


    private fun sinOut(){
        requireActivity().finishAffinity()
        FirebaseAuth.getInstance().signOut()

    }


    fun user(){
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("users").document(user).get()
            .addOnSuccessListener {
               usuario = it.get("nombre").toString()
                binding.txtUser.text = usuario

    }

}


    fun mostrarComentUser(){
        lifecycleScope.launch{
            FirebaseBD().getlistSimpleNameTurismo()
        }
        
    }

}