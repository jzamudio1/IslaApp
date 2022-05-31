package com.jzamudio.isla21410.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.databinding.ItemEmpresaUserBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar


class EmpresaUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresaUserBinding.bind(view)

    fun render(comentUser: ComentUser) {
        //documentEditar = comentUser.nombreDoc.toString()
       // Log.i("docEditar", "doceditar " + ClickEmpresas.documentEditar)
        binding.txtComentario.text = comentUser.nombre
    }

}