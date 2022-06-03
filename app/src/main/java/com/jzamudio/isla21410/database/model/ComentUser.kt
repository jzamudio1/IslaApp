package com.jzamudio.isla21410.database.model

/**
 * Modelo que pertenece a la empresa inserta por un usuario
 */
data class ComentUser(
    /**
     * UID del usuario
     */
    var uid: String? = null,
    /**
     *correo de la empresa
     */
    var correo: String? = null,
    /**
     *Descripcion de la empresa
     */
    var descripcion: String? = null,
    /**
     *Direccion de la empresa
     */
    var direccion: String? = null,
    /**
     *Foto de la empresa
     */
    var foto: String? = null,
    /**
     *Nombre de la empresa
     */
    var nombre: String? = null,
    /**
     *Telefono de la empresa
     */
    var telefono: String? = null,
    /**
     *Pagina Web de la empresa
     */
    var paginaweb: String? = "",
    /**
     *Documento dentro de firebase al que pertenece la empresa
     */
    var nombreDoc:String? = null
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