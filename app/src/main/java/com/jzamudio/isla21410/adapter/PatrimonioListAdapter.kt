package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.database.model.Turismo

class PatrimonioListAdapter(
    private val listInicio: List<Turismo>
) : RecyclerView.Adapter<PatrimonioListViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatrimonioListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PatrimonioListViewHolder(layoutInflater.inflate(R.layout.item_cardview_adapter, parent, false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: PatrimonioListViewHolder, position: Int) {
        val item = listInicio[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listInicio.size


}