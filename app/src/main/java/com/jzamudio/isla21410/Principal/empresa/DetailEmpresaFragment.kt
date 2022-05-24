package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore

import com.jzamudio.isla21410.adapter.ValoracionesAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.valoraciones
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import kotlinx.coroutines.launch

class DetailEmpresaFragment : Fragment() {

    private var _binding: FragmentDetailEmpresaBinding? = null
    private val binding get() = _binding!!
    val user = FirebaseAuth.getInstance().currentUser!!.uid


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DetailEmpresaFragmentArgs.fromBundle(requireArguments())
        // Inflate the layout for this fragment
        _binding = FragmentDetailEmpresaBinding.inflate(inflater, container, false)


        binding.txtNombreEmpresa.text = args.nombre
        binding.txtCorreo.text = args.correo
        binding.txtTelefono.text = args.telefono.toString()
        binding.btnAddComent.setOnClickListener {
            binding.comentario.text
        }

        lifecycleScope.launch {
            binding.listComentarios.adapter = ValoracionesAdapter(FirebaseBD().getlistvaloraciones())
            binding.listComentarios.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }

        binding.btnAddComent.setOnClickListener {
            insertEmpresa()
        }



        return binding.root
    }

    fun insertEmpresa() {
        FirebaseFirestore.getInstance().collection("users").document("${user}").get()
            .addOnSuccessListener {
                val valoraciones = valoraciones(
                    binding.comentario.text.toString(),
                    it.get("nombre").toString()
                )
                FirebaseBD().insertComentario(valoraciones).addOnSuccessListener {
                    refreshList()
                }

            }
    }

    fun refreshList() {
        lifecycleScope.launch {
            binding.listComentarios.adapter = ValoracionesAdapter(FirebaseBD().getlistvaloraciones())
        }
    }


}