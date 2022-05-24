package com.jzamudio.isla21410.util

import android.app.Application

class ClickTurismo  : Application() {

    companion object{
        const val Fiesta = 0
        const val Gastronomia = 1
        const val Naturaleza = 2
        const val Ocio = 3
        const val Patrimonio = 4
        const val Playas = 5
var tipo = 0
        lateinit var coleccionTurismo:String
        lateinit var documentTurismo:String

    }
}