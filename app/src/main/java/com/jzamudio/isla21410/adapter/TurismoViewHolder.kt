package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.TurismoListFragmentDirections
import com.jzamudio.isla21410.database.model.Turismo
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.squareup.picasso.Picasso
/**
 * ViewHolder que maneja TurismoAdapter
 */
class TurismoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)

    /**
     * Establecemos el nombre del item, y su foto.
     * Pasasmos por argumento el objeto Turismo para obtenerlos en la vista de Detalles.
     */
    fun render(turismo: Turismo) {
        binding.txtCardView.text = turismo.Nombre
        Picasso.get().load(Uri.parse(turismo.Imagen)).into(binding.imgCarView)
        binding.btnCardView.setOnClickListener {
            it.findNavController().navigate(
                TurismoListFragmentDirections.actionTurismoListFragmentToDetailTurismoFragment(
                    turismo.Nombre!!,
                    turismo.Descripcion!!,
                    turismo.Imagen!!,
                    turismo.latitud!!.toFloat(),
                    turismo.longitud!!.toFloat()
                )
            )
        }

    }
}