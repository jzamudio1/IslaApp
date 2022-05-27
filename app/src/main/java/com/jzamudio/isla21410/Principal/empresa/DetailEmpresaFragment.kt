package com.jzamudio.isla21410.Principal.empresa

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.R

import com.jzamudio.isla21410.adapter.ValoracionesAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.valoraciones
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.launch

class DetailEmpresaFragment : Fragment() {

    private var _binding: FragmentDetailEmpresaBinding? = null
    private val binding get() = _binding!!
    val user = FirebaseAuth.getInstance().currentUser!!.uid


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailEmpresaBinding.inflate(inflater, container, false)
        val args = DetailEmpresaFragmentArgs.fromBundle(requireArguments())
        // Inflate the layout for this fragment

        lifecycleScope.launch {
            binding.listComentarios.adapter =
                ValoracionesAdapter(FirebaseBD().getlistvaloraciones())

        }



        val number = Uri.parse("tel:" + args.telefono)

        binding.txtNombreEmpresa.text = args.nombre
        binding.txtCorreo.text = args.correo
        binding.txtTelefono.text = args.telefono
        Picasso.get().load(Uri.parse(args.foto)).into(binding.imgEmpresa)
        binding.txtTelefono.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse(number.toString()))
            requireActivity().startActivity(intent)

        }

        binding.btnAddComent.setOnClickListener {


        }




        return binding.root
    }

    fun insertComentario() {
        FirebaseFirestore.getInstance().collection("users").document("${user}").get()
            .addOnSuccessListener {
                val valoraciones = valoraciones(
                    it.get("nombre").toString(),
                    it.get("comentario").toString()
                )
                FirebaseBD().insertComentario(valoraciones).addOnSuccessListener {
                    refreshList()
                }

            }
    }

    fun refreshList() {
        lifecycleScope.launch {
            binding.listComentarios.adapter =
                ValoracionesAdapter(FirebaseBD().getlistvaloraciones())
        }
    }

    fun dialog(){
    val builder = AlertDialog.Builder(requireContext())
    val view = layoutInflater.inflate(R.layout.dialogcomentario,null)
    builder.setView(view)
    val dialog = builder.create()
    dialog.show()
}

}