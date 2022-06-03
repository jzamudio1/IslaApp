package com.jzamudio.isla21410.database.model

/**
 * Modelo empresas que pertenecen a las empresas estaticas de Firebase
 */

data class Empresa(
    var uid: String? = null,
    /**
     *Nombre de la empres
     */
    val nombre: String,
    /**
     *Direccion de la empresa
     */
    val direccion: String,
    /**
     *Telefono de la empresa
     */
    val telefono: String,
    /**
     *Correo de la empresa
     */
    val correo: String,
    /**
     *Pagina Web de la empresa
     */
    val paginaWeb: String,
    /**
     *Foto de la empresa
     */
    val foto: String,
    /**
     *Descripcion de la empresa
     */
    val descripcion: String,
)
