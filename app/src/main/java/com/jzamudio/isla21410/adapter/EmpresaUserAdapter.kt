package com.jzamudio.isla21410.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jzamudio.isla21410.Principal.configuration.ConfigurateViewModel
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar

/**
 * Adapter que muestra la Lista de Empresas de un usuario y el boton de editar y borrar.
 */

class EmpresaUserAdapter(
    private val listEmpresaInit: List<ComentUser>,
    private val viewModel: ConfigurateViewModel
) :
    RecyclerView.Adapter<EmpresaUserViewHolder>() {

    /**
     *Bandera que dice si se esta editando
     */
    private val _editar = MutableLiveData<ComentUser?>()
    val editar: LiveData<ComentUser?> get() = _editar
    /**
     *Bandera que dice si se ha borrado
     */
    private val _borrar = MutableLiveData<ComentUser?>()
    val borrar: LiveData<ComentUser?> get() = _borrar

    /**
     *Data Class ComentUser
     */
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

        /**
         *Click boton de borrado
         * Se establece el nombreDoc
         * y asignamos el valor a la bandera
         */
        holder.binding.imgButtonDelette.setOnClickListener {
            documentEditar = item.nombreDoc
            _borrar.value = item
        }

        /**
         *Click boton de editar
         * Se establece el nombreDoc
         * y asignamos el valor a la bandera
         */
        holder.binding.imgButtonEditar.setOnClickListener {
            _editar.value = item
            documentEditar = item.nombreDoc


        }


    }

    /**
     * Restablemecemos las banderas por defecto
     *
     */
    fun onDialogClose() {
        _editar.value = null
        _borrar.value = null
    }

    //Devuelve el numero de elementos
    override fun getItemCount(): Int = listEmpresaInit.size


}
