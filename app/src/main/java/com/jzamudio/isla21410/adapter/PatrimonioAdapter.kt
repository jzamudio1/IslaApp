package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.Patrimonio


class PatrimonioAdapter(
  private val listadoPatrim:List<Patrimonio>
) : RecyclerView.Adapter<PatrimonioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatrimonioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PatrimonioViewHolder(
            layoutInflater.inflate(
                R.layout.item_patrimonio_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PatrimonioViewHolder, position: Int) {
        val item = listadoPatrim[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listadoPatrim.size

}