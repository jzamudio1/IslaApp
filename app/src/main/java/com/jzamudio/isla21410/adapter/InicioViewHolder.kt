package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.InicioFragmentDirections
import com.jzamudio.isla21410.Principal.turismo.TurismoListFragmentDirections
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.jzamudio.isla21410.util.ClickTurismo.Companion.coleccionTurismo
import com.squareup.picasso.Picasso

class InicioViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)

    fun render(inicio: SimpleName) {

        Picasso.get().load(Uri.parse(inicio.foto)).into(binding.imgCarView)
        binding.txtCardView.text = inicio.nombre

        binding.btnCardView.setOnClickListener {
            coleccionTurismo = inicio.nombre
                it.findNavController()
                    .navigate(InicioFragmentDirections.actionNavigationTurismoToTurismoListFragment())


            if(coleccionTurismo == "patrimonio"){
                it.findNavController()
                     .navigate(TurismoListFragmentDirections.actionTurismoListFragmentToPatrimonioListFragment())
            }




        }
    }

}