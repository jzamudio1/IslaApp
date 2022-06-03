package com.jzamudio.isla21410.Principal.turismo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.SimpleName
import kotlinx.coroutines.launch
/**
 * ViewModel que maneja InicioFragment
 */
class InicioViewModel : ViewModel() {

    val listInit = mutableListOf<SimpleName>()
    val adaptador: InicioAdapter = InicioAdapter(listInit)

    init {
        adapter()
    }

    /**
     * Metodo que carga en el adapter la lista de Zonas de interes
     */
    private fun adapter(){
        viewModelScope.launch {
            FirebaseBD().getlistSimpleNameTurismo().forEach {
                listInit.add(it)
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }

        }
    }

}