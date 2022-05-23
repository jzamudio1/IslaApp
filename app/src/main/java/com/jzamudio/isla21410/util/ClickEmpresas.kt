package com.jzamudio.isla21410.util

import android.app.Application


class ClickEmpresas : Application(){

    companion object {
        const val abogado = 0
        const val autoescuela = 1
        const val alimentacion = 2
        const val farmacias = 3
        const val ferreterias = 4
        const val inmobiliarias = 5
        const val manodeObra = 6
        const val moda = 7
        const val peluquerias = 8
        const val restauracion = 9
        const val talleres = 10
        const val taxis = 11
        const val veterinario = 12
        var tipo = 0

        lateinit var coleccion:String
        lateinit var documento:String

    }



}
