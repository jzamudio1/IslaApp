package com.jzamudio.isla21410.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar

class EmpresaUserAdapter(private val listEmpresaInit: List<ComentUser>)
    : RecyclerView.Adapter<EmpresaUserViewHolder>() {


    private val _live = MutableLiveData<ComentUser>()
    val live: LiveData<ComentUser> get() = _live

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return EmpresaUserViewHolder(layoutInflater.inflate(R.layout.item_empresa_user, parent, false))
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: EmpresaUserViewHolder, position: Int) {
        val item = listEmpresaInit[position]
        holder.render(item)

        Log.i("idEmpresa", "adapter " + item.toString())

        holder.binding.imgButtonEditar.setOnClickListener {
            _live.value = item
            documentEditar = listEmpresaInit[position].nombreDoc.toString()
            Log.i("docEditar", "doceditar " + documentEditar)
        }


    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size


}