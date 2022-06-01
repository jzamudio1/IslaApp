package com.jzamudio.isla21410.util

import android.app.Application


class ClickEmpresas : Application(){


    companion object {
        lateinit var coleccionEmpresas:String
        lateinit var documentoEmpresas:String
        var documentEditar:String? = null
        var flagLogin:Boolean = true
        var flagInvitado:Boolean = false



    }



}
