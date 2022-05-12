package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.Patrimonio
import com.jzamudio.isla21410.databinding.ItemPatrimonioAdapterBinding
import com.jzamudio.isla21410.Principal.turismo.patrimonio.edificios.listEdificios.CasasFragmentDirections

import com.squareup.picasso.Picasso

class PatrimonioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemPatrimonioAdapterBinding.bind(view)

    fun render(patrimonio: Patrimonio){

        binding.txtNombres.text = patrimonio.Nombre
        Picasso.get().load(Uri.parse(patrimonio.Foto)).into(binding.imgCasas)

        binding.btnVerCasas.setOnClickListener {

            it.findNavController().navigate(CasasFragmentDirections.actionCasasFragmentToPlantillaCasasFragment(patrimonio.Foto,patrimonio.Descripcion))

        }
    }
}