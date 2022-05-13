package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.*
import com.jzamudio.isla21410.util.ClickListner
import kotlinx.coroutines.tasks.await


class FirebaseBD {

    val firebaseInstance = FirebaseFirestore.getInstance()


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
                            correo = doc["correo"].toString()
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
                            correo = doc["correo"].toString()
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

        firebaseInstance.collection("inicio").get()
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