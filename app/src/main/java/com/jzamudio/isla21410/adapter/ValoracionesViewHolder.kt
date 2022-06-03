package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.valoraciones
import com.jzamudio.isla21410.databinding.ItemValoracionesAdapterBinding
/**
 * viewHolder que maneja ValoracionesAdapter
 */
class ValoracionesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemValoracionesAdapterBinding.bind(view)

    /**
     *Metodo que estableces el nombre y el comentario en la vista de detalles de empresa
     */
    fun render(valoraciones: valoraciones) {
        binding.txtNombreUser.text = valoraciones.usuario
        binding.txtComentario.text = valoraciones.comentario

    }

}
