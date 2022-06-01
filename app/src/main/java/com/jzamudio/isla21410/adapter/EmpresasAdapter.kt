package com.jzamudio.isla21410.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.Empresa


class EmpresasAdapter(private val listEmpresaServicios: List<ComentUser>) :
    RecyclerView.Adapter<EmpresasViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpresasViewHolder(
            layoutInflater.inflate(
                R.layout.item_cardview_adapter,
                parent,
                false
            )
        )
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: EmpresasViewHolder, position: Int) {
        val item = listEmpresaServicios[position]
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaServicios.size


}