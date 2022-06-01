package com.jzamudio.isla21410.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.valoraciones
import com.jzamudio.isla21410.databinding.ItemValoracionesAdapterBinding

class ValoracionesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemValoracionesAdapterBinding.bind(view)

    fun render(valoraciones: valoraciones) {
        binding.txtNombreUser.text = valoraciones.usuario
        binding.txtComentario.text = valoraciones.comentario

    }

}
