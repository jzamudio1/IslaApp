package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.*
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.coleccion
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documento
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.restauracion
import com.jzamudio.isla21410.util.ClickTurismo.Companion.coleccionTurismo
import kotlinx.coroutines.tasks.await

class FirebaseBD {

    val firebaseInstance = FirebaseFirestore.getInstance()


    /**
     * Obtener Lista Valoraciones
     */
    suspend fun getlistvaloraciones(): List<valoraciones> {

        val listValoraciones = mutableListOf<valoraciones>()

        firebaseInstance.collection("/$coleccion/$documento/comentarios").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listValoraciones.add(
                        valoraciones(
                            comentario = doc["comentario"].toString(),
                            usuario = doc["usuario"].toString()

                        )
                    )
                }
            }.await()

        return listValoraciones
    }

    /**
     * Obtener Lista Detalles Empresa
     */
    suspend fun getDetailEmpresa(): List<Empresa> {

        val listTalleres = mutableListOf<Empresa>()

        firebaseInstance.collection("$coleccion").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listTalleres.add(
                        Empresa(
                            carta = doc["nombre"].toString(),
                            correo = doc["correo"].toString(),
                            telefono = doc["telefono"].toString(),
                            nombre = doc["nombre"].toString(),
                            descripcion = doc["nombre"].toString(),
                            paginaWeb = doc["nombre"].toString(),
                            foto = doc["nombre"].toString(),
                        )
                    )
                }
            }.await()
        return listTalleres
    }
    /**
     * Insertar una Empresa
     */
    fun insertEmpresa(tipo: String, empresa: Empresa) {

        firebaseInstance.collection(tipo).document(empresa.nombre)
            .set(
                hashMapOf(
                    "nombre" to empresa.nombre.toString(),
                    "telefono" to empresa.telefono.toString(),
                    "correo" to empresa.correo.toString(),
                    "paginaWeb" to empresa.paginaWeb.toString(),
                    "foto" to empresa.foto.toString(),
                    "descripcion" to empresa.descripcion.toString(),
                    "carta" to empresa.carta.toString()
                )
            )
    }
    /**
     * Insertar un comentario
     */
    fun insertComentario(valoraciones: valoraciones): Task<Void> {

        return firebaseInstance.collection("$coleccion").document("$documento")
            .collection("comentarios").document()
            .set(
                hashMapOf(

                    "comentario" to valoraciones.comentario.toString(),
                    "usuario" to valoraciones.usuario.toString()


                )
            )


    }

    suspend fun getlistSimpleName(): List<SimpleName> {

        val listSimpleName = mutableListOf<SimpleName>()

        firebaseInstance.collection("$restauracion").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listSimpleName.add(
                        SimpleName(
                            nombre = doc["nombre"].toString()
                        )
                    )
                }
            }.await()

        return listSimpleName
    }

    suspend fun getlistpatrimonios(): List<Patrimonio> {

        val listPatrimonioName = mutableListOf<Patrimonio>()

        firebaseInstance.collection("/patrimonio").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listPatrimonioName.add(
                        Patrimonio(
                            nombre = doc["nombre"].toString()
                        )
                    )
                }
            }.await()

        return listPatrimonioName
    }




    /**
     * Devuelve lista de Turismo de BBDD
     */
    suspend fun getlistTurismo(): List<Turismo> {

        val listTurismo = mutableListOf<Turismo>()

        firebaseInstance.collection("${coleccionTurismo}").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listTurismo.add(
                        Turismo(
                            Imagen = doc["foto"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString(),
                            latitud = doc["latitud"].toString().toDouble(),
                            longitud = doc["longitud"].toString().toDouble()

                        )
                    )
                }
            }.await()
        Log.i("getlistTurismo", "$listTurismo")
        return listTurismo
    }


    suspend fun getlistPlayas(): List<Turismo> {

        val listTurismos = mutableListOf<Turismo>()

        firebaseInstance.collection("images").document("playa").collection("playaCentral").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listTurismos.add(
                        Turismo(
                            Imagen = doc["imagen"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString(),
                            latitud = doc["latitud"].toString().toDouble(),
                            longitud = doc["longitud"].toString().toDouble(),

                            )
                    )

                }
            }.await()

        return listTurismos

    }

}