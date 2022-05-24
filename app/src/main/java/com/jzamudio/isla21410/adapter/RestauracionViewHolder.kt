package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.listSubCategoriasEmpresaFragmentDirections
import com.jzamudio.isla21410.database.conexion.FirebaseBD

import com.jzamudio.isla21410.database.model.SimpleName

import com.jzamudio.isla21410.databinding.ItemRestauracionAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas


class RestauracionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemRestauracionAdapterBinding.bind(view)

    fun render(simpleName: SimpleName) {

    binding.txtUsuario.text = simpleName.nombre

        binding.btnVerCasas.setOnClickListener {
            ClickEmpresas.restauracion = simpleName.nombre

            it.findNavController().navigate(listSubCategoriasEmpresaFragmentDirections.actionListSubCategoriasEmpresaFragmentToDetailSubCategoriaEmpresaFragment())

        }
    }

}