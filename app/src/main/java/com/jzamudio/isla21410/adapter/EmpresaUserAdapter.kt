package com.jzamudio.isla21410.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.configuration.ConfigurateFragment
import com.jzamudio.isla21410.Principal.configuration.ConfigurateViewModel
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar


class EmpresaUserAdapter(
    private val listEmpresaInit: List<ComentUser>,
    private val viewModel: ConfigurateViewModel
) :
    RecyclerView.Adapter<EmpresaUserViewHolder>() {


    private val _editar = MutableLiveData<ComentUser?>()
    val editar: LiveData<ComentUser?> get() = _editar
    private val _borrar = MutableLiveData<ComentUser?>()

    val borrar: LiveData<ComentUser?> get() = _borrar
    var posicion = 0
    lateinit var comentUser: ComentUser

    //Infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpresaUserViewHolder(
            layoutInflater.inflate(
                R.layout.item_empresa_user,
                parent,
                false
            )
        )
    }

    //Defino el comportamiento que va a tener el adapter con los objetos
    override fun onBindViewHolder(holder: EmpresaUserViewHolder, position: Int) {
        val item = listEmpresaInit[position]

        holder.render(item)

        Log.i("idEmpresa", "adapter " + item.toString())

        holder.binding.imgButtonDelette.setOnClickListener {
            documentEditar = item.nombreDoc
            _borrar.value = item
        }

        holder.binding.imgButtonEditar.setOnClickListener {
            _editar.value = item
            documentEditar = item.nombreDoc


        }


    }

    fun onDialogClose() {
        _editar.value = null
        _borrar.value = null
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size


}
