package com.jzamudio.isla21410.Principal.empresa

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.jzamudio.isla21410.Principal.configuration.ConfigurateFragment
import com.jzamudio.isla21410.Principal.configuration.ConfigurateViewModel
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.databinding.FragmentEmpresaEditBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class EmpresaEditFragment(
    private val comentUser: ComentUser? = null,
    private val viewModel: ConfigurateViewModel

) : DialogFragment() {

    private lateinit var binding: FragmentEmpresaEditBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEmpresaEditBinding.inflate(layoutInflater)

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onView()

        Picasso.get().load(Uri.parse(comentUser!!.foto)).into(binding.imgFotoEmpresa)
        binding.btnGuardarEmpresa.setOnClickListener {
            onGuardar()
        }


        return binding.root
    }


    fun onView() {
        binding.etNombreEmpresa.setText(comentUser!!.nombre.toString())
        binding.etCorreo.setText(comentUser!!.correo.toString())
        binding.etDireccion.setText(comentUser!!.direccion.toString())
        binding.etTelefono.setText(comentUser!!.telefono.toString())
        binding.etDescripcion.setText(comentUser!!.descripcion.toString())
        binding.etPaginaWeb.setText(comentUser!!.paginaweb).toString()

    }

    fun onGuardar() {
        comentUser?.nombre = binding.etNombreEmpresa.text.toString()
        comentUser?.correo = binding.etCorreo.text.toString()
        comentUser?.direccion = binding.etDireccion.text.toString()
        comentUser?.telefono = binding.etTelefono.text.toString()
        comentUser?.descripcion = binding.etDescripcion.text.toString()
        onEditEmpresa()
    }

    fun onEditEmpresa() {
        lifecycleScope.launch {
            val id = FirebaseBD().getListIdEmpresa()
            FirebaseBD().actualizarEmpresa(id, comentUser!!)
            viewModel.rechear()
            dialog?.dismiss()
        }
    }


    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.adaptador.onDialogClose()
    }


}