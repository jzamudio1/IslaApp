package com.jzamudio.isla21410.database.conexion

import android.graphics.pdf.PdfDocument
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.*
import kotlinx.coroutines.tasks.await


class FirebaseBD {

    val firebaseInstance = FirebaseFirestore.getInstance()

    suspend fun getlistEmpresaInit():List<EmpresaInit>{

        val listEmpresaInit = mutableListOf<EmpresaInit>()

        firebaseInstance.collection("empresas").get()
            .addOnSuccessListener {

                for (doc in it){
                    listEmpresaInit.add(
                        EmpresaInit(
                            nombre = doc["nombre"].toString()

                        )
                    )
                }
            }.await()
        Log.i("listaEmpresa","$listEmpresaInit")
        return listEmpresaInit
    }

    suspend fun getlistEmpresas():List<Empresa>{

        val listEmpresa = mutableListOf<Empresa>()

        firebaseInstance.collection("empresas/Servicios/Hotel").get()
            .addOnSuccessListener {

                for (doc in it){
                    listEmpresa.add(
                        Empresa(
                            nombre = doc["nombre"].toString(),
                            foto = doc["nombre"].toString(),
                            descripcion = doc["nombre"].toString(),
                            paginaWeb = doc["nombre"].toString(),
                            telefono = doc["nombre"] as Int,
                            correo = doc["nombre"].toString()
                      )
                    )
                }
            }.await()
        Log.i("listaEmpresa","$listEmpresa")
        return listEmpresa
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

        return listPlayas

    }

}