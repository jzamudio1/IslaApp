package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.SimpleName

/**
 * Adapter que muestra la lista de zonas de interes de Turismo
 */
class InicioAdapter(
    private val listInicio: List<SimpleName>
) : RecyclerView.Adapter<InicioViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InicioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return InicioViewHolder(layoutInflater.inflate(R.layout.item_cardview_adapter, parent, false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: InicioViewHolder, position: Int) {
        val item = listInicio[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listInicio.size


}