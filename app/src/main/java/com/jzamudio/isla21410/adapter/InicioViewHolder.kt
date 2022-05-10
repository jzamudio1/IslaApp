package com.jzamudio.isla21410.adapter


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.Inicio
import com.jzamudio.isla21410.databinding.ItemInicioAdapterBinding


class InicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemInicioAdapterBinding.bind(view)

    fun render(inicio: Inicio, onClickListner: (Inicio) -> Unit) {
        binding.txtPlayas.text = inicio.nombre

        binding.btnInicio.setOnClickListener {
            onClickListner(inicio)
        }


    }
}