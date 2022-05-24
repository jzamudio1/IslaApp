package com.jzamudio.isla21410.database.model

import com.google.firebase.firestore.GeoPoint


data class Playa(
    val Imagen: String?=null,
    val Nombre: String?=null,
    val Descripcion: String?=null,
    val latitud:Double?=0.0,
    val longitud:Double?=0.0

)

