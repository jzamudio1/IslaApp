package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.EmpresasFragmentDirections
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.ItemEmpresaInitAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas

class EmpresaInitViewHolder  (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresaInitAdapterBinding.bind(view)


    fun render(empresainit: SimpleName){
        binding.txtAlojamiento.text = empresainit.nombre
        binding.btnAlojamiento.setOnClickListener {
            ClickEmpresas.tipo = adapterPosition
            ClickEmpresas.coleccion = empresainit.nombre
            it.findNavController().navigate(EmpresasFragmentDirections.actionNavigationEmpresasToListEmpresaFragment())

        }
    }
}