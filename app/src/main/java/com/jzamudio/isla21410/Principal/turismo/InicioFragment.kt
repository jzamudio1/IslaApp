package com.jzamudio.isla21410.Principal.turismo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.R

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
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnBeach.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_turismo_to_playasFragment)
        }
        binding.btnPatrimonio.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_listPatrimonioFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}