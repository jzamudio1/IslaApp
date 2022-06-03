package com.jzamudio.isla21410.Principal.empresa

import android.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.adapter.ValoracionesAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.valoraciones
import kotlinx.coroutines.launch
/**
 * Clase Detalles Empresas ViewMOdel
 */

class DetailEmpresaViewModel(
    val foto: String,
    val nombre: String,
    val descripcion: String,
    val correo: String,
    val telefono: String,
    val direccion: String,
    val paginaweb: String,
    val fragment:DetailEmpresaFragment
) : ViewModel() {
    /**
     * Lista de comentarios
     */
    val listComentario = mutableListOf<valoraciones>()
    /**
     * Adapter
     */
    val adaptador: ValoracionesAdapter = ValoracionesAdapter(listComentario)
    /**
     * Comentario del usuario introducido en el Alert
     */
    private lateinit var comentario: String

    /**
     * Factory
     */
    class Factory(
        val foto: String,
        val nombre: String,
        val descripcion: String,
        val correo: String,
        val telefono: String,
        val direccion: String,
        val paginaweb: String,
        val fragment: DetailEmpresaFragment
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailEmpresaViewModel(
                foto,
                nombre,
                descripcion,
                correo,
                telefono,
                direccion,
                paginaweb,
                fragment
            ) as T
        }

    }


    init {
        cargarComentarios()
    }

    /**
     * Metodo que Carga en el adapter la lista de los comentarios
     */
    private fun cargarComentarios() {
        viewModelScope.launch {
            FirebaseBD().getlistvaloraciones().forEach {
                listComentario.add(it)
                adaptador.notifyItemInserted(listComentario.indexOf(it))
            }
        }
    }


    /**
     * Metodo que Muestra un dialogo para Insertar un comentario.
     */
    private fun alertDialogDemo() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
        builder.setTitle("Añade Tu Comentario!!!!")
        // Set up the input
        val input = EditText(fragment.requireContext())
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.hint = "Introduce tu comentario."
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { _, _ ->
            // Here you get get input text from the Edittext
            comentario = input.text.toString()
            Log.i("comentario", comentario)
            insertComentario()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }


    private fun insertComentario() {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("users").document(user).get()
            .addOnSuccessListener {
                val valoraciones = valoraciones(
                    comentario = comentario,
                    usuario = it.get("nombre").toString(),
                )

                FirebaseBD().insertComentario(valoraciones,user).addOnSuccessListener {
                    listComentario.add(valoraciones)
                    adaptador.notifyItemInserted(listComentario.indexOf(valoraciones))
                }

            }
    }


    fun isConected() {
        val usuario = FirebaseAuth.getInstance().currentUser?.uid
        Log.i("usuario", usuario.toString())
        if (usuario != null) {
            alertDialogDemo()
        } else {

        }
    }
}
