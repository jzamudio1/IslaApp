package com.jzamudio.isla21410.database.model

/**
 *Model de las Valoraciones
 */
data class valoraciones(
    /**
     *Comentario de un usuario
     */
    val comentario: String,
    /**
     *Usuario que lo ha escrito
     */
    val usuario: String
)
