package com.jzamudio.isla21410.adapter

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.listEmpresaFragmentArgs
import com.jzamudio.isla21410.Principal.turismo.Playas.listPlayas.Camping.PlayaCampingFragmentArgs
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.ItemEmpresasAdapterBinding


class EmpresasViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresasAdapterBinding.bind(view)
    val args = listEmpresaFragmentArgs.fromBundle(Bundle.EMPTY)
    fun render(empresa: Empresa){

        binding.txtNombreEmpresa.text = args.nombre

    }
}