package com.jzamudio.isla21410.database.model

import android.net.Uri

data class Empresa(
    var uid: String? = null,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    val paginaWeb: String,
    val foto: String,
    val descripcion: String,
)
