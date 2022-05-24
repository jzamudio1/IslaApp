package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.Turismo

class PlayaAdapter(
    private val listTurismo:List<Turismo>
) : RecyclerView.Adapter<PlayaViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PlayaViewHolder(layoutInflater.inflate(R.layout.item_playa_adapter,parent,false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: PlayaViewHolder, position: Int) {
        val item = listTurismo[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listTurismo.size



}