package com.jzamudio.isla21410.Principal.empresa


import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import com.squareup.picasso.Picasso


class DetailEmpresaFragment : Fragment() {

    private var _binding: FragmentDetailEmpresaBinding? = null
    private val binding get() = _binding!!

    private lateinit var args: DetailEmpresaFragmentArgs


    private lateinit var viewModel: DetailEmpresaViewModel
    private lateinit var viewModelFactory: DetailEmpresaViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailEmpresaBinding.inflate(inflater, container, false)
        args = DetailEmpresaFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = DetailEmpresaViewModel.Factory(args.foto,args.nombre,args.descripcion,args.correo,args.telefono,args.direccion,args.paginaweb,this)
        viewModel = ViewModelProvider(this,viewModelFactory)[DetailEmpresaViewModel::class.java]

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
           viewModel.isConected()
        }

        return binding.root
    }

    private fun cargarComentarios() {
        binding.listadoComentarios.adapter = viewModel.adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listadoComentarios.layoutManager = linearLayoutManager
    }


    fun argumentos() {
        binding.txtNombreEmpresa.text = viewModel.nombre
        binding.txtCorreo.text = viewModel.correo
        binding.txtTelefono.text = viewModel.telefono
        binding.txtDireccion.text = viewModel.direccion
        binding.txtPaginaWeb.text = viewModel.paginaweb
        addLikns()
        validacion()
    }


    fun validacion() {
        if (viewModel.nombre.isEmpty() || viewModel.nombre == null) {
            binding.txtNombreEmpresa.text = "No Tiene Nombre"
        }
        if (viewModel.correo.isEmpty() || viewModel.correo == null) {
            binding.txtCorreo.text = "No tiene Correo"
        }

        if (viewModel.direccion.isEmpty()|| viewModel.direccion== null) {
            binding.txtDireccion.text = "No tiene Direccion"

            if (viewModel.telefono.isEmpty()|| viewModel.telefono== null) {
                binding.txtTelefono.text = "No tiene TLF"
            }
            if (viewModel.paginaweb.isEmpty()|| viewModel.paginaweb== null) {
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