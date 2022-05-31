package com.jzamudio.isla21410.Principal.empresa


import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.adapter.ValoracionesAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.Empresa
import com.jzamudio.isla21410.database.model.valoraciones
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class DetailEmpresaFragment : Fragment() {

    private var _binding: FragmentDetailEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var comentario: String
    private lateinit var args: DetailEmpresaFragmentArgs
    val listComentario = mutableListOf<valoraciones>()
    private lateinit var adaptador: ValoracionesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEmpresaBinding.inflate(inflater, container, false)
        args = DetailEmpresaFragmentArgs.fromBundle(requireArguments())
        // Inflate the layout for this fragment
        Picasso.get().load(Uri.parse(args.foto)).into(binding.imgEmpresa)
        argumentos()
        val number = Uri.parse("tel:" + args.telefono)

        /* binding.txtTelefono.setOnClickListener {
             val intent = Intent(Intent.ACTION_DIAL)
             intent.data = Uri.parse(number.toString())
             requireActivity().startActivity(intent)
         }*/

        cargarComentarios()

        binding.btnAddComent.setOnClickListener {
           isConected()
        }

        return binding.root
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
                    cargarComentarios()
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



    private fun alertDialogDemo() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Añade Tu Comentario!!!!")
        // Set up the input
        val input = EditText(requireContext())
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


    private fun cargarComentarios() {
        listComentario.clear()
        adaptador = ValoracionesAdapter(listComentario)
        binding.listadoComentarios.adapter = adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listadoComentarios.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            listComentario.addAll(FirebaseBD().getlistvaloraciones())
            adaptador.notifyDataSetChanged()
        }
    }


    fun argumentos() {
        binding.txtNombreEmpresa.text = args.nombre
        binding.txtCorreo.text = args.correo
        binding.txtTelefono.text = args.telefono
        binding.txtDireccion.text = args.direccion
        binding.txtPaginaWeb.text = args.paginaweb
        addLikns()
        validacion()
    }


    fun validacion() {
        if (args.nombre.isEmpty() || args.nombre == null) {
            binding.txtNombreEmpresa.text = "No Tiene Nombre"
        }
        if (args.correo.isEmpty() || args.correo== null) {
            binding.txtCorreo.text = "No tiene Correo"
        }

        if (args.direccion.isEmpty()|| args.direccion== null) {
            binding.txtDireccion.text = "No tiene Direccion"

            if (args.telefono.isEmpty()|| args.telefono== null) {
                binding.txtTelefono.text = "No tiene TLF"
            }
            if (args.paginaweb.isEmpty()|| args.paginaweb== null) {
                binding.txtPaginaWeb.text = "No tiene WEB"
            }
        }


    }

    fun addLikns() {
        Linkify.addLinks(binding.txtPaginaWeb, Linkify.WEB_URLS)
        Linkify.addLinks(binding.txtCorreo, Linkify.EMAIL_ADDRESSES)
        Linkify.addLinks(binding.txtTelefono, Linkify.PHONE_NUMBERS)
        Linkify.addLinks(binding.txtDireccion, Linkify.MAP_ADDRESSES)
    }
}