package com.jzamudio.isla21410.Principal.turismo.patrimonio.edificios.plantilla

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.databinding.FragmentPlantillaCasasBinding
import com.squareup.picasso.Picasso

class plantillaCasasFragment : Fragment() {
    //Binding
    private var _binding: FragmentPlantillaCasasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantillaCasasBinding.inflate(inflater, container, false)

        //val args = plantillaCasasFragmentArgs.fromBundle(requireArguments())


       // Picasso.get().load(Uri.parse(args.foto)).into(binding.imgPlaya)
       // binding.txtDescripcion.text = args.descripcion

        // Inflate the layout for this fragment
        return binding.root
    }

}