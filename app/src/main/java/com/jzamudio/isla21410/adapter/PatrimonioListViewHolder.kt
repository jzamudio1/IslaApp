package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.turismo.InicioFragmentDirections
import com.jzamudio.isla21410.Principal.turismo.PatrimonioListFragmentDirections
import com.jzamudio.isla21410.Principal.turismo.TurismoListFragmentDirections
import com.jzamudio.isla21410.database.model.Patrimonio
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.database.model.Turismo
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.jzamudio.isla21410.util.ClickTurismo

class PatrimonioListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)

    fun render(turismo: Turismo) {

        binding.txtCardView.text = turismo.Nombre

        binding.btnCardView.setOnClickListener {
            ClickTurismo.coleccionTurismo=turismo.Nombre!!
                it.findNavController()
                    .navigate(PatrimonioListFragmentDirections.actionPatrimonioListFragmentToDetailTurismoFragment2(
                        turismo.Nombre!!,turismo.Imagen!!,turismo.Descripcion!!,turismo.latitud!!.toFloat(),turismo.longitud!!.toFloat()))
            }

        }
}