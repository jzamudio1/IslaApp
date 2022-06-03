package com.jzamudio.isla21410.Principal.turismo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
/**
 * ViewModelDetailTurismo
 */
class DetailTurismoViewModel(val foto:String,val nombre:String,val descripcion:String,val latitud:Float, val longitud:Float) : ViewModel() {

    /**
     * Factory
     */
    class Factory(val foto:String,val nombre:String,val descripcion:String,val latitud:Float, val longitud:Float): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailTurismoViewModel(foto,nombre,descripcion,latitud,longitud) as T
        }
    }
}