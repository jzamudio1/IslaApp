package com.jzamudio.isla21410.database.model

import android.graphics.pdf.PdfDocument

data class Empresa(
    val nombre:String,
    val telefono: String,
    val correo:String,
    val paginaWeb:String,
    val foto:String,
    val descripcion:String,
    val carta:String
)
