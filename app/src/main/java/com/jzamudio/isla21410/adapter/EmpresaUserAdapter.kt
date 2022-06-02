package com.jzamudio.isla21410.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.configuration.ConfigurateFragment
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar


class EmpresaUserAdapter(
    private val listEmpresaInit: List<ComentUser>,
    private val context: Context,
    private val fragment: ConfigurateFragment,
    var rechearAdapter: Boolean
) :
    RecyclerView.Adapter<EmpresaUserViewHolder>() {


    private val _live = MutableLiveData<ComentUser>()
    val live: LiveData<ComentUser> get() = _live
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
            showDialogAlertSimple()
            documentEditar = listEmpresaInit[position].nombreDoc.toString()
            posicion = position
        }

        holder.binding.imgButtonEditar.setOnClickListener {
            _live.value = item
            documentEditar = listEmpresaInit[position].nombreDoc.toString()


        }


    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size


    fun showDialogAlertSimple() {
        AlertDialog.Builder(context)
            .setTitle("Titulo del diálogo")
            .setMessage("Contenido del diálogo.")
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                    fragment.delete()
                    fragment.deleteItem(posicion)
                    rechearAdapter = false

                })
            .setNegativeButton(android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón cancel pulsado
                })
            .show()
    }
}
