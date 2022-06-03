package com.jzamudio.isla21410.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.databinding.ItemEmpresaUserBinding


/**
 * ViewHolder que maneja EmpresaUserAdappter
 */
class EmpresaUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemEmpresaUserBinding.bind(view)

    /**
     * Asignamos el nombre de la empresa.
     */
    fun render(comentUser: ComentUser) {
        binding.txtComentario.text = comentUser.nombre
    }


}