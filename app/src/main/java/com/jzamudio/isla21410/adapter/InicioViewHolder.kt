package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.InicioFragmentDirections
import com.jzamudio.isla21410.Principal.turismo.Playas.playasFragmentDirections
import com.jzamudio.isla21410.database.model.Inicio
import com.jzamudio.isla21410.database.model.Playa
import com.jzamudio.isla21410.databinding.ItemInicioAdapterBinding
import com.jzamudio.isla21410.databinding.ItemPlayaAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickTurismo
import com.squareup.picasso.Picasso

class InicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemInicioAdapterBinding.bind(view)

    fun render(inicio: Inicio){

        binding.txtBelleza.text = inicio.nombre

        binding.btnAlojamiento.setOnClickListener {
            ClickTurismo.tipo = adapterPosition
            it.findNavController().navigate(InicioFragmentDirections.actionNavigationTurismoToPlayasFragment())

        }
    }

}