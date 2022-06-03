package com.jzamudio.isla21410.util

/**
 * Clase Global de empresa que contiene un companion Objetc
 * Se usa para buscar en las coleciones y documentos de firebase
 */


class ClickEmpresas{


    companion object {
        /**
         * Coleccion a la que pertenece las empresas.
         */
        lateinit var coleccionEmpresas:String
        /**
         * Documento al que pertenece la empresa.
         */
        lateinit var documentoEmpresas:String
        /**
         * Documento que tiene que editar.
         */
        var documentEditar:String? = null
        /**
         * Flag que comprueba si el usuario se ha loguedo
         */
        var flagLogin:Boolean = true



    }



}
