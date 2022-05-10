package com.jzamudio.isla21410

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.adapter.InicioAdapter

import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.Inicio
import com.jzamudio.isla21410.databinding.FragmentInicioBinding
import kotlinx.coroutines.launch

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



        lifecycleScope.launch {
            binding.recycleInicio.adapter = InicioAdapter(FirebaseBD().getAllInicio()) { inicio ->
                onItemSelect(
                    inicio
                )
            }
            binding.recycleInicio.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemSelect(inicio: Inicio) {


    }
}