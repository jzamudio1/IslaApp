package com.jzamudio.isla21410.database.model

data class ComentUser(
    var uid: String? = null,
    var correo: String? = null,
    var descripcion: String? = null,
    var direccion: String? = null,
    var foto: String? = null,
    var nombre: String? = null,
    var telefono: String? = null,
    var paginaweb: String? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf<String, Any?>(
            "uid" to uid,
            "correo" to correo,
            "nombre" to nombre,
            "paginaweb" to paginaweb,
            "telefono" to telefono,
            "descripcion" to descripcion,
            "foto" to foto,
            "direccion" to direccion
        )
    }
}