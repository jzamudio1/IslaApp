package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.databinding.FragmentDetailEmpresaBinding
import com.jzamudio.isla21410.util.ClickListner

class DetailEmpresaFragment : Fragment() {

    private var _binding: FragmentDetailEmpresaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DetailEmpresaFragmentArgs.fromBundle(requireArguments())
        // Inflate the layout for this fragment
        _binding = FragmentDetailEmpresaBinding.inflate(inflater, container, false)


        ClickListner.Alojamiento=true
        ClickListner.Servicio=true

        binding.txtNombreEmpresa.text = args.nombre
        binding.txtCorreo.text = args.correo
        binding.txtTelefono.text = args.telefono.toString()
        binding.txtDescripcion.text = args.descripcion
        
        return binding.root
    }


}