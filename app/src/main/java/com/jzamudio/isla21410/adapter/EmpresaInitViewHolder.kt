package com.jzamudio.isla21410.adapter

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.EmpresasFragmentDirections
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.squareup.picasso.Picasso
/**
 * ViewHolder que maneja EmpresaInitAdapter
 */

class EmpresaInitViewHolder  (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)

    /**
     * Metodo que Recibe el nombre de las categorias de empresas y le establece
     * la foto, el nombre y navega segun el item clicado
     */

    fun render(empresainit: SimpleName){
        Picasso.get().load(Uri.parse(empresainit.foto)).into(binding.imgCarView)
        binding.txtCardView.text = empresainit.nombre
        binding.btnCardView.setOnClickListener {

            //ClickEmpresas.tipo = adapterPosition
            ClickEmpresas.coleccionEmpresas = empresainit.nombre
            it.findNavController().navigate(EmpresasFragmentDirections.actionNavigationEmpresasToListEmpresaFragment())

        }
    }
}