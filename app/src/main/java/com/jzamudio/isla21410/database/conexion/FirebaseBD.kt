package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.*
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.coleccionEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentoEmpresas
import com.jzamudio.isla21410.util.ClickTurismo.Companion.coleccionTurismo
import kotlinx.coroutines.tasks.await

class FirebaseBD {

    private val firebaseInstance = FirebaseFirestore.getInstance()


    /**
     * Obtener Lista Valoraciones
     */
    suspend fun getlistvaloraciones(): List<valoraciones> {

        val listValoraciones = mutableListOf<valoraciones>()

        firebaseInstance.collection("/$coleccionEmpresas/$documentoEmpresas/comentarios").get()
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
        Log.i("comentarios", listValoraciones.toString())
        return listValoraciones
    }

    /**
     * Obtener Lista Detalles Empresa
     */
    suspend fun getDetailEmpresa(): List<Empresa> {

        val listTalleres = mutableListOf<Empresa>()

        firebaseInstance.collection(coleccionEmpresas).get()
            .addOnSuccessListener {

                for (doc in it) {
                    listTalleres.add(
                        Empresa(
                            direccion = doc["direccion"].toString(),
                            correo = doc["correo"].toString(),
                            telefono = doc["telefono"].toString(),
                            nombre = doc["nombre"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            paginaWeb = doc["paginaweb"].toString(),
                            foto = doc["foto"].toString(),
                        )
                    )
                }
            }.await()
        return listTalleres
    }

    /**
     * Obtener Lista Detalles Empresa
     */
    suspend fun getDetailRestauracion(): List<Restauracion> {

        val listRestauracion = mutableListOf<Restauracion>()

        firebaseInstance.collection(coleccionEmpresas).get()
            .addOnSuccessListener {

                for (doc in it) {
                    listRestauracion.add(
                        Restauracion(
                            nombre = doc["nombre"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            telefono = doc["telefono"].toString(),
                            foto = doc["foto"].toString(),
                            carta = doc["carta"].toString(),
                            direccion = doc["direccion"].toString(),

                            )
                    )
                }
            }.await()
        return listRestauracion
    }

    /**
     * Insertar una Empresa
     */
    fun insertEmpresa(tipo: String, empresa: Empresa) {

        firebaseInstance.collection(tipo).document(empresa.nombre)
            .set(
                hashMapOf(
                    "nombre" to empresa.nombre,
                    "telefono" to empresa.telefono,
                    "correo" to empresa.correo,
                    "paginaWeb" to empresa.paginaWeb,
                    "foto" to empresa.foto,
                    "descripcion" to empresa.descripcion,
                    "direccion" to empresa.direccion
                )
            )
    }

    /**
     * Insertar un comentario
     */
    fun insertComentario(valoraciones: valoraciones, uid: String): Task<Void> {

        return firebaseInstance.collection(coleccionEmpresas).document(documentoEmpresas)
            .collection("comentarios").document()
            .set(
                hashMapOf(

                    "uid" to uid,
                    "comentario" to valoraciones.comentario,
                    "usuario" to valoraciones.usuario


                )
            )


    }


  /*  suspend fun getlistSimpleNameEdfs(): List<SimpleName> {

        val listSimpleNameEmpresa = mutableListOf<SimpleName>()
        var coleccion1: String
        var documento1: String
        val comentario = "comentarios"
        var docComent: String
        firebaseInstance.collection("/empresas").document().get()
            .addOnSuccessListener {
                coleccion1 = it.get
                for (doc in coleccion1) {
                    firebaseInstance.collection(coleccion1).get()
                        .addOnSuccessListener {
                            documento1 = it.documents.toString()
                            for (doc in documento1) {
                                firebaseInstance.collection(documento1).get()
                                    .addOnSuccessListener {
                                        firebaseInstance.collection(comentario).get()
                                            .addOnSuccessListener {
                                                docComent = it.documents.toString()
                                                for (doc in docComent) {
                                                    val user = FirebaseAuth.getInstance().currentUser!!.uid

                                                }
                                            }
                                    }

                            }


                        }.await()


                }
                return listSimpleNameEmpresa
            }*/

        suspend fun getlistSimpleNameEmpresas(): List<SimpleName> {

            val listSimpleNameEmpresa = mutableListOf<SimpleName>()

            firebaseInstance.collection(coleccionEmpresas).get()
                .addOnSuccessListener {

                    for (doc in it) {
                        listSimpleNameEmpresa.add(
                            SimpleName(
                                nombre = doc["nombre"].toString(),
                                foto = doc["foto"].toString()
                            )
                        )
                    }
                }.await()

            return listSimpleNameEmpresa
        }

        suspend fun getlistSimpleNameTurismo(): List<SimpleName> {

            val listSimpleNameTurismo = mutableListOf<SimpleName>()

            firebaseInstance.collection(coleccionTurismo).get()
                .addOnSuccessListener {

                    for (doc in it) {
                        listSimpleNameTurismo.add(
                            SimpleName(
                                nombre = doc["nombre"].toString(),
                                foto = doc["foto"].toString()
                            )
                        )
                    }
                }.await()

            return listSimpleNameTurismo
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

            firebaseInstance.collection(coleccionTurismo).get()
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