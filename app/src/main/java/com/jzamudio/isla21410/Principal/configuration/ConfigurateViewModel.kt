package com.jzamudio.isla21410.Principal.configuration

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.adapter.EmpresaUserAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import kotlinx.coroutines.launch

/**
 * Clase ViewModel Configurate
 */
class ConfigurateViewModel : ViewModel() {
    /**
     *Nombre del USuario
     */
    private var _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> get() = _usuario

    /**
     * Lista que va a cargar el Adapter
     */
    val listInit = mutableListOf<ComentUser>()

    /**
     * Bandera ProgresBarr
     */
    private val _flagProgres = MutableLiveData<Boolean>()
    val flagProgres: LiveData<Boolean> get() = _flagProgres

    /**
     * Adapted
     */

    var adaptador: EmpresaUserAdapter = EmpresaUserAdapter(listInit, this)


    init {

        user()

    }

    /**
     * Metodo que borra la empresa del Usuario
     */
    fun delete() {
        _flagProgres.value = true
        viewModelScope.launch {
            val id = FirebaseBD().getListIdEmpresa()
            FirebaseBD().borraEmpresa(id)
            val index = listInit.indexOf(adaptador.borrar.value)
            listInit.removeAt(index)
            adaptador.notifyItemRemoved(index)
            adaptador.onDialogClose()
            _flagProgres.value = false
        }
    }

    /**
     * Metodo que carga las empresas registradas por un Usuario
     */
    fun cargarEmpresas() {
        viewModelScope.launch {
            listInit.clear()
            val id = FirebaseBD().getListIdEmpresa()
            Log.i("idEmpresa", id.toString())
            FirebaseBD().getListDocEmpresa(id).forEach {
                listInit.add(it)
                Log.i("idEmpresa", it.nombre.toString())
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }
        }

    }

    /**
     * Metodo que comprueba el usuario conectado y establece su Nombre
     */
    fun user() {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("users").document(user).get()
            .addOnSuccessListener {
                _usuario.value = it.get("nombre").toString()
            }
    }

    /**
     * Metodo que recarga el reciclerView cuando se edita una Empresa
     */
    fun rechear() {
        adaptador.notifyItemChanged(listInit.indexOf(adaptador.editar.value))
    }

}
