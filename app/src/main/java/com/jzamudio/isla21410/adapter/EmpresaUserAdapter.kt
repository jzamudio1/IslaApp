package com.jzamudio.isla21410.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.databinding.ItemEmpresaUserBinding

class EmpresaUserAdapter (private val listEmpresaInit:List<ComentUser>) : RecyclerView.Adapter<EmpresaUserAdapter.EmpresaUserViewHolder>() {

    inner class EmpresaUserViewHolder(val binding: ItemEmpresaUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val _live = MutableLiveData<ComentUser>()
    val live: LiveData<ComentUser> get() = _live
    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaUserViewHolder {
        val binding = ItemEmpresaUserBinding.inflate(LayoutInflater.from(parent.context))

        return EmpresaUserViewHolder(binding)
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: EmpresaUserViewHolder, position: Int) {
        val item = listEmpresaInit[position]
        holder.binding.txtComentario.text = item.nombre
        Log.i("idEmpresa", "adapter "+item.toString())
        holder.binding.imageButton.setOnClickListener {
            _live.value = item
        }




    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size



}