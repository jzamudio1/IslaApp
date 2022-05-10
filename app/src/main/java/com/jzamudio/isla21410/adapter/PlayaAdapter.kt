package com.jzamudio.isla21410.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.Playa
import com.jzamudio.isla21410.databinding.ItemPlayaAdapterBinding
import com.squareup.picasso.Picasso


class PlayaAdapter(
    private val listPlaya:List<Playa>
) : RecyclerView.Adapter<PlayaAdapter.PlayaViewHolder>() {

    inner class PlayaViewHolder(val binding: ItemPlayaAdapterBinding):RecyclerView.ViewHolder(binding.root)

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayaViewHolder {
        val binding = ItemPlayaAdapterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PlayaViewHolder(binding)
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: PlayaViewHolder, position: Int) {
        //PlayaCentral
        holder.binding.txtPlayaCentral.text = listPlaya[position].Nombre
        Picasso.get().load(listPlaya[position].Imagen)
            .into(holder.binding.imgPlayaCentral)

    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listPlaya.size


}