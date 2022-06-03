package com.jzamudio.isla21410.Principal.turismo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.TurismoAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.Turismo
import kotlinx.coroutines.launch

/**
 * ViewModel que maneja TurismoList
 */
class TurismoListViewModel : ViewModel() {


    val listInit = mutableListOf<Turismo>()
    val adaptador: TurismoAdapter = TurismoAdapter(listInit)

    init {
        onAdapter()
    }

    /**
     * Metodo que carga en el adapter la lista de items que pertenecen a la zona de interes
     */
    private fun onAdapter() {
        viewModelScope.launch {
            FirebaseBD().getlistTurismo().forEach {
                listInit.add(it)
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }
        }


    }
}