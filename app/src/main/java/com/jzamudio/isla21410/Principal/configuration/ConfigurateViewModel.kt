package com.jzamudio.isla21410.Principal.configuration

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.adapter.EmpresaUserAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import kotlinx.coroutines.launch

class ConfigurateViewModel : ViewModel() {
    private var _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> get() = _usuario
    val listInit = mutableListOf<ComentUser>()
    private val _flagProgres = MutableLiveData<Boolean>()
    val flagProgres: LiveData<Boolean> get() = _flagProgres

    var adaptador: EmpresaUserAdapter = EmpresaUserAdapter(listInit, this)


    init {
        user()

    }

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


    fun user() {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("users").document(user).get()
            .addOnSuccessListener {
                _usuario.value = it.get("nombre").toString()
            }
    }


    fun rechear() {
        adaptador.notifyItemChanged(listInit.indexOf(adaptador.editar.value))
    }

}
