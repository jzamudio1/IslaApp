package com.jzamudio.isla21410.util

import android.app.Application


class ClickEmpresas : Application(){

    companion object {
        const val Comida = 0
        const val Servicio = 1
        const val Alojamiento = 2
        const val playa = 4
        const val casas = 5
        const val nada = 0
        var tipo = 0

    }



}
