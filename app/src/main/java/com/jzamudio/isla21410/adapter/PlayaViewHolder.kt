package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView


import com.jzamudio.isla21410.database.model.Turismo
import com.jzamudio.isla21410.databinding.ItemPlayaAdapterBinding
import com.squareup.picasso.Picasso

class PlayaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPlayaAdapterBinding.bind(view)


    fun render(turismo: Turismo){

        binding.txtPlayaCentral.text = turismo.Nombre
        Picasso.get().load(Uri.parse(turismo.Imagen)).into(binding.imgPlayaCentral)
        binding.btnPlayaCentral.setOnClickListener {
           // it.findNavController().navigate(PlantillaTurismoFragmentDirections.action_plantillaTurismoFragment_to_playaDetailFragment)
        }
        }

    }
