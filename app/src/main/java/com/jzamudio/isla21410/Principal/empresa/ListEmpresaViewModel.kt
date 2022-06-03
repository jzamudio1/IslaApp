package com.jzamudio.isla21410.Principal.empresa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import kotlinx.coroutines.launch
/**
 * ViewModel que maneja la ListEmpresaFragment
 */
class ListEmpresaViewModel : ViewModel() {
    val listInit = mutableListOf<ComentUser>()
    val adaptador: EmpresasAdapter = EmpresasAdapter(listInit)

    init {
        onAdapter()
    }


    /**
     *Metodo que carga en el adapter la lista de items quer pertenece a la categoria de la empresa
     */
    private fun onAdapter() {
        viewModelScope.launch {
            FirebaseBD().getDetailEmpresa().forEach {
                listInit.add(it)
                adaptador.notifyItemInserted(listInit.indexOf(it))
            }

        }
    }
}
