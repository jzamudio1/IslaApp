package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.Playas.playasFragmentDirections
import com.jzamudio.isla21410.database.model.Playa
import com.jzamudio.isla21410.databinding.ItemPlayaAdapterBinding
import com.squareup.picasso.Picasso

class PlayaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPlayaAdapterBinding.bind(view)

    fun render(playa: Playa){

        binding.txtPlayaCentral.text = playa.Nombre
        Picasso.get().load(Uri.parse(playa.Imagen)).into(binding.imgPlayaCentral)

        binding.btnPlayaCentral.setOnClickListener {

            it.findNavController().navigate(playasFragmentDirections.actionListPlayasToPlayaCampingFragment(playa.Imagen,playa.Descripcion))

        }
        }

    }
