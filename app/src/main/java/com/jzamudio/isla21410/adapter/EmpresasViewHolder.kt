package com.jzamudio.isla21410.adapter


import android.net.Uri
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.listEmpresaFragmentDirections
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentoEmpresas
import com.squareup.picasso.Picasso


class EmpresasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)


    fun render(empresa: Empresa) {
        binding.txtCardView.text = empresa.nombre
        Picasso.get().load(Uri.parse(empresa.foto)).into(binding.imgCarView)
        binding.btnCardView.setOnClickListener {
            documentoEmpresas = empresa.nombre

                it.findNavController().navigate(
                    listEmpresaFragmentDirections.actionListEmpresaFragmentToDetailEmpresaFragment(
                        empresa.nombre, empresa.descripcion, empresa.foto, empresa.correo,
                        empresa.telefono, empresa.paginaWeb,empresa.direccion
                    )
                )
            }
        }
    }
