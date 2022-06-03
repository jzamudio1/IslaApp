package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.jzamudio.isla21410.database.model.*
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.coleccionEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentEditar
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.documentoEmpresas
import com.jzamudio.isla21410.util.ClickTurismo.Companion.coleccionTurismo

import kotlinx.coroutines.tasks.await

class FirebaseBD {

    private val firebaseInstance = FirebaseFirestore.getInstance()


    /**
     * Obtener Lista de comentarios.
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
     * Obtener la lista de las empresas
     */
    suspend fun getDetailEmpresa(): List<ComentUser> {

        val listDatilEmpresa = mutableListOf<ComentUser>()

        firebaseInstance.collection(coleccionEmpresas).get()
            .addOnSuccessListener {

                for (doc in it) {
                    listDatilEmpresa.add(
                        ComentUser(

                            nombreDoc = doc["nombreDoc"].toString(),
                            direccion = doc["direccion"].toString(),
                            correo = doc["correo"].toString(),
                            telefono = doc["telefono"].toString(),
                            nombre = doc["nombre"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            paginaweb = doc["paginaweb"].toString(),
                            foto = doc["foto"].toString(),
                            uid = doc["uid"].toString()
                        )
                    )
                }
            }.await()
        return listDatilEmpresa
    }


    /**
     * Insertar una Empresa
     */
    fun insertEmpresa(tipo: String, empresa: ComentUser) {

        firebaseInstance.collection(tipo).document(empresa.nombre!!)
            .set(
                hashMapOf(
                    "uid" to empresa.uid,
                    "nombre" to empresa.nombre,
                    "direccion" to empresa.direccion,
                    "telefono" to empresa.telefono,
                    "correo" to empresa.correo,
                    "paginaweb" to empresa.paginaweb,
                    "foto" to empresa.foto,
                    "descripcion" to empresa.descripcion,
                    "nombreDoc" to empresa.nombre
                )
            )
    }

    /**
     * Insertar un comentario
     */
    fun insertComentario(valoraciones: valoraciones, uid: String): Task<Void> {

        Log.i("caca", "caca" + coleccionEmpresas)
        Log.i("caca", "caca" + documentoEmpresas)
        return firebaseInstance.collection(coleccionEmpresas).document(documentoEmpresas)
            .collection("/comentarios").document()
            .set(
                hashMapOf(

                    "uid" to uid,
                    "comentario" to valoraciones.comentario,
                    "usuario" to valoraciones.usuario


                )
            )


    }

    /**
     * Devuelve una lista de las ID Coleccion
     */

    suspend fun getListIdEmpresa(): List<String> {
        var listID = mutableListOf<String>()
        FirebaseBD().getListUserEmpresa().addOnSuccessListener { coleccion1 ->
            for (docEmpresa in coleccion1) {

                listID.add(
                    docEmpresa.id

                )
            }

        }.await()
        return listID
    }

    /**
     * Borra una empresa que pertenece a un usuario.
     *
     */
    suspend fun borraEmpresa(ids: List<String>) {
        for (id in ids) {
            Log.i("docRefe", "ID " + id.toString())
            FirebaseBD().getListUserbyUID(id)
                .addOnSuccessListener { coleccion2 ->
                    for (docUID in coleccion2) {
                        if (docUID.reference.id == documentEditar) {
                            Log.i("docRefe", "Coment " + docUID.reference.id)
                            Log.i("docRefe", "docEditar " + documentEditar)
                            firebaseInstance.collection(id).document(documentEditar!!).delete()
                            firebaseInstance.collection(id).document(documentEditar!!)
                                .collection("/comentarios").get().addOnSuccessListener {
                                for (doc in it) {
                                    doc.reference.delete()
                                }
                            }


                        }
                    }
                }.await()
        }
    }

    /**
     * Actualiza una empresa recibiendo el id de las colecciones
     * buscando en ellas el documento a actualizar
     */

    suspend fun actualizarEmpresa(ids: List<String>, comentUser: ComentUser) {
        for (id in ids) {
            Log.i("docRefe", "ID " + id.toString())
            FirebaseBD().getListUserbyUID(id)
                .addOnSuccessListener { coleccion2 ->
                    for (docUID in coleccion2) {
                        if (docUID.reference.id == documentEditar) {
                            Log.i("docRefe", "Coment " + docUID.reference.id)
                            Log.i("docRefe", "docEditar " + documentEditar)
                            firebaseInstance.collection(id).document(documentEditar!!)
                                .update(comentUser.toMap())
                        }
                    }
                }.await()
        }
    }

    /**
     * Obtiene el id De la coleccion y busca en todos los documentos
     * la que tenga el mismo uid que el usuario conectado
     */
    suspend fun getListDocEmpresa(ids: List<String>): List<ComentUser> {
        val listEmpresaEdit = mutableListOf<ComentUser>()
        for (id in ids) {
            FirebaseBD().getListUserbyUID(id)
                .addOnSuccessListener { coleccion2 ->
                    for (docUID in coleccion2) {
                        val comentUser = docUID.toObject(ComentUser::class.java)
                        if (comentUser.uid == Firebase.auth.currentUser?.uid) {
                            listEmpresaEdit.add(
                                comentUser
                            )

                        }
                    }
                }.await()
        }
        return listEmpresaEdit
    }


    /**
     * Recibe uid que es el nombre de la Coleccion por la que buscar
     */
    fun getListUserbyUID(uid: String): Task<QuerySnapshot> {
        return firebaseInstance.collection(uid).get()
    }

    fun getListUserbyUID2(uid: String): CollectionReference {
        return firebaseInstance.collection(uid)
    }


    /**
     * Devuelve Todos los nombres de los documentos de la coleccion Empresas
     */

    fun getListUserEmpresa(): Task<QuerySnapshot> {
        return firebaseInstance.collection("/empresas").get()
    }

    /**
     * Devuelve El nombre y la foto De TEmpresas
     */
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

    /**
     * Devuelve El nombre y la foto De Turismo
     */

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


}