package com.jzamudio.isla21410.Principal.empresa


import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope

import com.jzamudio.isla21410.adapter.EmpresaInitAdapter

import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.SimpleName
import kotlinx.coroutines.launch
/**
 * ViewModel que maneja EmpresaFragment
 */

class EmpresaViewModel : ViewModel() {

    val listInit = mutableListOf<SimpleName>()
    val adaptador: EmpresaInitAdapter = EmpresaInitAdapter(listInit)

    init {
        onAdapter()
    }


    /**
     *Metodo que carga en el adapter la lista de Categorias de la empresa
     */
    private fun onAdapter() {
        viewModelScope.launch {
            FirebaseBD().getlistSimpleNameEmpresas().forEach {
                listInit.add(it)
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }
        }
    }

}