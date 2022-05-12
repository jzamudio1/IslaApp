package com.jzamudio.isla21410.adapter

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.EmpresasFragmentDirections
import com.jzamudio.isla21410.Principal.turismo.patrimonio.edificios.listEdificios.CasasFragmentDirections
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.database.model.EmpresaInit
import com.jzamudio.isla21410.databinding.ItemEmpresaInitAdapterBinding
import com.jzamudio.isla21410.databinding.ItemEmpresasAdapterBinding

class EmpresaInitViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresaInitAdapterBinding.bind(view)

    fun render(empresaInit: EmpresaInit){

        binding.txtAlojamiento.text = empresaInit.nombre

        binding.btnAlojamiento.setOnClickListener {

            it.findNavController().navigate(EmpresasFragmentDirections.actionNavigationEmpresasToListEmpresaFragment())

        }
    }
}