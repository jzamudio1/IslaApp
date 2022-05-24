package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.InicioFragmentDirections
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.ItemInicioAdapterBinding
import com.jzamudio.isla21410.util.ClickTurismo.Companion.coleccionTurismo

class InicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemInicioAdapterBinding.bind(view)

    fun render(inicio: SimpleName){

        binding.txtBelleza.text = inicio.nombre

        binding.btnAlojamiento.setOnClickListener {
            coleccionTurismo = inicio.nombre
            if(coleccionTurismo == "Playas"){
                coleccionTurismo = "/images/playa/playaCentral"
            }

            it.findNavController().navigate(InicioFragmentDirections.actionNavigationTurismoToPlantillaTurismoFragment())

        }
    }

}