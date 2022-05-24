package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.Playas.PlantillaTurismoFragmentDirections
import com.jzamudio.isla21410.database.model.Patrimonio
import com.jzamudio.isla21410.database.model.Turismo
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
class TurismoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)


    fun render(turismo: Turismo){
        binding.txtCardView.text = turismo.Nombre
        binding.btnCardView.setOnClickListener {
            it.findNavController().navigate(PlantillaTurismoFragmentDirections.actionPlantillaTurismoFragmentToPlayaDetailFragment(
                    turismo.Nombre!!, turismo.Descripcion!!, turismo.Imagen!!, turismo.latitud!!.toFloat(), turismo.longitud!!.toFloat())
            )
            }

        }

}