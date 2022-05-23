package com.jzamudio.isla21410.database.model

import com.google.firebase.firestore.GeoPoint


data class Playa(
    val Imagen: String,
    val Nombre: String,
    val Descripcion: String,
    val latitud:String,
    val longitud:String
)

