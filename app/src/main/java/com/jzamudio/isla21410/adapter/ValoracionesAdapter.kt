package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.valoraciones

class ValoracionesAdapter(
    private val listValoraciones:List<valoraciones>
) : RecyclerView.Adapter<ValoracionesViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValoracionesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ValoracionesViewHolder(layoutInflater.inflate(R.layout.item_valoraciones_adapter,parent,false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: ValoracionesViewHolder, position: Int) {
        val item = listValoraciones[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listValoraciones.size



}