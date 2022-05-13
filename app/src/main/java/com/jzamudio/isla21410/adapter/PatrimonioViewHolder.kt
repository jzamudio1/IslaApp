package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.Patrimonio
import com.jzamudio.isla21410.databinding.ItemPatrimonioAdapterBinding
import com.jzamudio.isla21410.databinding.ItemPlayaAdapterBinding


import com.squareup.picasso.Picasso

class PatrimonioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPlayaAdapterBinding.bind(view)

    fun render(patrimonio: Patrimonio){

        binding.txtPlayaCentral.text = patrimonio.Nombre
        //Picasso.get().load(Uri.parse(patrimonio.Foto)).into(binding.imgCasas)


    }
}