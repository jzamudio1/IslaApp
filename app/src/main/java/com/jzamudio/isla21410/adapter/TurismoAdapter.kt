package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.Turismo
/**
 *Adapter que muestra los itemns que poertenecen a las zonas de interes de turismo
 */

class TurismoAdapter(
    private val listadoTurismo: List<Turismo>
) : RecyclerView.Adapter<TurismoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurismoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return TurismoViewHolder(
            layoutInflater.inflate(
                R.layout.item_cardview_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TurismoViewHolder, position: Int) {
        val item = listadoTurismo[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listadoTurismo.size

}