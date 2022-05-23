package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.*
import kotlinx.coroutines.tasks.await


class FirebaseBD {

    val firebaseInstance = FirebaseFirestore.getInstance()
    val firebaseBD = FirebaseDatabase.getInstance().getReference()


    suspend fun insertEmpresa(tipo:String, empresa : Empresa){


        firebaseBD.child("/$tipo/")
        firebaseBD.push().setValue(empresa)

    }

    suspend fun getlistAutoescuelas():List<Empresa> {

        val listAutoescuelas = mutableListOf<Empresa>()

        firebaseInstance.collection("/AutoEscuelas").get()
            .addOnSuccessListener {

                for (doc in it) {
                    listAutoescuelas.add(
                        Empresa(
                            carta = doc["carta"].toString(),
                            correo = doc["correo"].toString(),
                            telefono = doc["telefono"].toString(),
                            nombre = doc["nombre"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            paginaWeb = doc["imagen"].toString(),
                            foto = doc["nombre"].toString(),
                        )
                    )
                }
            }.await()
        return listAutoescuelas
    }
        suspend fun getlistAlimentacion():List<Empresa> {

            val listAlimentacion = mutableListOf<Empresa>()

            firebaseInstance.collection("/Alimentacion").get()
                .addOnSuccessListener {

                    for (doc in it) {
                        listAlimentacion.add(
                            Empresa(
                                carta = doc["carta"].toString(),
                                correo = doc["correo"].toString(),
                                telefono = doc["telefono"].toString(),
                                nombre = doc["nombre"].toString(),
                                descripcion = doc["descripcion"].toString(),
                                paginaWeb = doc["imagen"].toString(),
                                foto = doc["nombre"].toString(),
                            )
                        )
                    }
                }.await()
            return listAlimentacion
        }
            suspend fun getlistRestaurantes():List<Empresa> {

                val listRestaurantes = mutableListOf<Empresa>()

                firebaseInstance.collection("/patrimonio").get()
                    .addOnSuccessListener {

                        for (doc in it) {
                            listRestaurantes.add(
                                Empresa(
                                    carta = doc["carta"].toString(),
                                    correo = doc["correo"].toString(),
                                    telefono = doc["telefono"].toString(),
                                    nombre = doc["nombre"].toString(),
                                    descripcion = doc["descripcion"].toString(),
                                    paginaWeb = doc["imagen"].toString(),
                                    foto = doc["nombre"].toString(),
                                )
                            )
                        }
                    }.await()
                return listRestaurantes
            }



    suspend fun getlistpatrimonios():List<Playa>{

        val listPlayas = mutableListOf<Playa>()

        firebaseInstance.collection("/patrimonio").get()
            .addOnSuccessListener {

                for (doc in it){
                    listPlayas.add(
                        Playa(
                            Imagen = doc["imagen"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString()
                        )
                    )
                }
            }.await()

        Log.i("listPlayas","$listPlayas")

        return listPlayas

    }

    suspend fun getlistNaturaleza():List<Playa>{

        val listPlayas = mutableListOf<Playa>()

        firebaseInstance.collection("/naturaleza").get()
            .addOnSuccessListener {

                for (doc in it){
                    listPlayas.add(
                        Playa(
                            Imagen = doc["imagen"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString()
                        )
                    )
                }
            }.await()

        Log.i("listPlayas","$listPlayas")

        return listPlayas

    }



    suspend fun getlistEmpresaInit():List<EmpresaInit>{

        val listEmpresaInit= mutableListOf<EmpresaInit>()

        firebaseInstance.collection("/empresas").get()
            .addOnSuccessListener {

                for (doc in it){
                    listEmpresaInit.add(
                        EmpresaInit(
                            nombre = doc["nombre"].toString()
                        )
                    )
                    //Integer.parseInt(doc["telefono"].toString())
                }
            }.await()
        Log.i("listaEmpresa","$listEmpresaInit")
        return listEmpresaInit
    }

    suspend fun getlistEmpresasServicio():List<Empresa>{

        val listEmpresaServicio= mutableListOf<Empresa>()

        firebaseInstance.collection("/empresas/ServicioTuristicos/serviciosturs").get()
            .addOnSuccessListener {

                for (doc in it){
                    listEmpresaServicio.add(
                        Empresa(
                            nombre = doc["nombre"].toString(),
                            foto = doc["foto"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            paginaWeb = doc["paginaweb"].toString(),
                            telefono = doc["telefono"].toString(),
                            correo = doc["correo"].toString(),
                            carta = doc["carta"].toString(),
                        )
                    )
                    //Integer.parseInt(doc["telefono"].toString())
                }
            }.await()
        Log.i("listaEmpresa","$listEmpresaServicio")
        return listEmpresaServicio
    }


    suspend fun getlistEmpresasAlojamiento():List<Empresa>{

        val listEmpresaAlojamiento = mutableListOf<Empresa>()

        firebaseInstance.collection("/empresas/alojamiento/Hotel").get()
            .addOnSuccessListener {

                for (doc in it){
                    listEmpresaAlojamiento.add(
                        Empresa(
                            nombre = doc["nombre"].toString(),
                            foto = doc["foto"].toString(),
                            descripcion = doc["descripcion"].toString(),
                            paginaWeb = doc["paginaweb"].toString(),
                            telefono = doc["telefono"].toString(),
                            correo = doc["correo"].toString(),
                            carta = doc["carta"].toString()
                      )
                    )
                }
            }.await()
        Log.i("listaEmpresa","$listEmpresaAlojamiento")
        return listEmpresaAlojamiento
    }


    suspend fun getlistPatrimonio():List<Patrimonio>{

        val listPatrimonio = mutableListOf<Patrimonio>()

        firebaseInstance.collection("patrimonio/edificio/casas").get()
            .addOnSuccessListener {

                for (doc in it){
                    listPatrimonio.add(
                        Patrimonio(
                            Foto = doc["foto"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString()
                        )
                    )
                }
            }.await()
        Log.i("listaPatrimonio","$listPatrimonio")
        return listPatrimonio
    }

    suspend fun getAllInicio():List<Inicio>{

        val listInicio = mutableListOf<Inicio>()

        firebaseInstance.collection("/inicio").get()
            .addOnSuccessListener {

                for (doc in it){
                    listInicio.add(
                        Inicio(
                            nombre = doc["nombre"].toString()
                        )
                    )
                }
            }.await()

        Log.i("db823","$listInicio")

        return listInicio
    }


   suspend fun getlistPlayas():List<Playa>{

    val listPlayas = mutableListOf<Playa>()

        firebaseInstance.collection("images/playa/playaCentral").get()
            .addOnSuccessListener {

                for (doc in it){
                    listPlayas.add(
                        Playa(
                            Imagen = doc["imagen"].toString(),
                            Nombre = doc["nombre"].toString(),
                            Descripcion = doc["descripcion"].toString()
                        )
                    )
                }
            }.await()

       Log.i("listPlayas","$listPlayas")

        return listPlayas

    }

}