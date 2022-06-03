package com.jzamudio.isla21410.database.model

/**
 * Modelo Turismo.
 */
data class Turismo(
    /**
     *Imagen del item de la zona de interes
     */
    val Imagen: String? = null,
    /**
     *Nombre del item de la zona de interes
     */
    val Nombre: String? = null,

    /**
     *Descripcion del item de la zona de interes
     */

    val Descripcion: String? = null,
    /**
     *Latitud del item de la zona de interes
     */
    val latitud: Double? = 0.0,
    /**
     *Longitud del item de la zona de interes
     */
    val longitud: Double? = 0.0

)


