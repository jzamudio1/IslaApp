package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.SimpleName


class RestauracionAdapter(
    private val listRestauracion:List<SimpleName>
) : RecyclerView.Adapter<RestauracionViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauracionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return RestauracionViewHolder(layoutInflater.inflate(R.layout.item_playa_adapter,parent,false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: RestauracionViewHolder, position: Int) {
        val item = listRestauracion[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listRestauracion.size



}