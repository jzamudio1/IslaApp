package com.jzamudio.isla21410.Principal.turismo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.SimpleName
import kotlinx.coroutines.launch

class InicioViewModel(val it: Int) : ViewModel() {

    class Factory(val it: Int):ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return InicioViewModel(it) as T
        }
    }


    val listInit = mutableListOf<SimpleName>()
    val adaptador: InicioAdapter = InicioAdapter(listInit)

    init {
        adapter()
    }


    private fun adapter(){
        viewModelScope.launch {
            FirebaseBD().getlistSimpleNameTurismo().forEach {
                listInit.add(it)
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }

        }
    }

}