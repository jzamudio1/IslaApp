package com.jzamudio.isla21410

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater,container,false)
        val root:View = binding.root

        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtAlojamientos.setText(it.get("Alojamiento") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtFiesta.setText(it.get("Fiesta") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtGastromia.setText(it.get("Gastronomia") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtIslantilla.setText(it.get("Islantilla") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtNaturaleza.setText(it.get("Naturaleza") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtOcio.setText(it.get("Ocio") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtPatrimonio.setText(it.get("Patrimonio") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtPlayas.setText(it.get("Playas") as String?)
        }
        db.collection("inicio").document("text").get().addOnSuccessListener {
            binding.txtRutas.setText(it.get("Rutas") as String?)
        }

        binding.Alojamiento.setOnClickListener {

        }
        binding.Fiesta.setOnClickListener {

        }

        binding.Gastronomia.setOnClickListener {

        }
        binding.Naturaleza.setOnClickListener {

        }
        binding.Islantilla.setOnClickListener {

        }
        binding.Ocio.setOnClickListener {

        }
        binding.txtPatrimonio.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_listPatrimonioFragment)
        }
        binding.Playas.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_playasFragment)
        }
        binding.Rutas.setOnClickListener {

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}