package com.jzamudio.isla21410.adapter


import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.listEmpresaFragmentDirections

import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.ItemEmpresaInitAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas


class EmpresasViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresaInitAdapterBinding.bind(view)


    fun render(empresa: Empresa){
        binding.txtAlojamiento.text = empresa.nombre
        binding.btnAlojamiento.setOnClickListener {
            ClickEmpresas.documento = empresa.nombre
            it.findNavController().navigate(listEmpresaFragmentDirections.actionListEmpresaFragmentToDetailEmpresaFragment(empresa.nombre,empresa.descripcion,empresa.foto,empresa.correo,
            empresa.telefono,empresa.paginaWeb))
        }
    }
}