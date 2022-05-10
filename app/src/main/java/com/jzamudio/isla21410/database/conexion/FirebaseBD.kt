package com.jzamudio.isla21410.database.conexion

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.database.model.Inicio
import com.jzamudio.isla21410.database.model.Playa
import kotlinx.coroutines.tasks.await


class FirebaseBD {

    val firebaseInstance = FirebaseFirestore.getInstance()


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

       Log.i("db823","$listPlayas")

        return listPlayas

    }

}