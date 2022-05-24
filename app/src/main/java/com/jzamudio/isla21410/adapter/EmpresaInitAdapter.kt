package com.jzamudio.isla21410.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.SimpleName

class EmpresaInitAdapter(private val listEmpresaInit:List<SimpleName>) : RecyclerView.Adapter<EmpresaInitViewHolder>() {

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaInitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpresaInitViewHolder(layoutInflater.inflate(R.layout.item_empresa_init_adapter,parent,false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: EmpresaInitViewHolder, position: Int) {
        val item = listEmpresaInit[position]
        //ClickListner.tipo = holder.getBindingAdapterPosition()
        holder.render(item)
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size



}
