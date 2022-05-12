package com.jzamudio.isla21410.database.model

import android.graphics.pdf.PdfDocument

data class Empresa(
    val nombre:String,
    val telefono: Int,
    val correo:String,
    val paginaWeb:String,
    val foto:String,
    val descripcion:String
)
