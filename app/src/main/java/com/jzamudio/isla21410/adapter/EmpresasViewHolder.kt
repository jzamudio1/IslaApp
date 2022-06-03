package com.jzamudio.isla21410.adapter


import android.net.Uri
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.empresa.listEmpresaFragmentDirections
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.ItemCardviewAdapterBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentoEmpresas
import com.squareup.picasso.Picasso


class EmpresasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemCardviewAdapterBinding.bind(view)


    fun render(comentUser: ComentUser) {
        binding.txtCardView.text = comentUser.nombre
        Picasso.get().load(Uri.parse(comentUser.foto)).into(binding.imgCarView)

        binding.btnCardView.setOnClickListener {
            if (comentUser.nombreDoc != null) {
                documentoEmpresas = comentUser.nombre.toString()
            }else{
                documentoEmpresas = comentUser.nombreDoc.toString()
            }

            Log.i("valoracionm", documentoEmpresas)
            it.findNavController().navigate(
                listEmpresaFragmentDirections.actionListEmpresaFragmentToDetailEmpresaFragment(
                    comentUser.nombre.toString(),
                    comentUser.descripcion.toString(),
                    comentUser.foto.toString(),
                    comentUser.correo.toString(),
                    comentUser.telefono.toString(),
                    comentUser.paginaweb.toString(),
                    comentUser.direccion.toString()
                )
            )
        }
    }
}
