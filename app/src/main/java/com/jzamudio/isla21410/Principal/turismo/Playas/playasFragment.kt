package com.jzamudio.isla21410.Principal.turismo.Playas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.adapter.PatrimonioAdapter
import com.jzamudio.isla21410.adapter.PlayaAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickTurismo
import com.jzamudio.isla21410.util.ClickTurismo.Companion.Naturaleza
import com.jzamudio.isla21410.util.ClickTurismo.Companion.Playas
import com.jzamudio.isla21410.util.ClickTurismo.Companion.patrimonios
import kotlinx.coroutines.launch

class playasFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    val storage = Firebase.storage
    val storageRef = storage.reference

    // Create a reference with an initial file path and name
    val pathReference = storageRef.child("images/stars.jpg")
    val mstorage = FirebaseStorage.getInstance().getReference()

    //Binding
    private var _binding: PlayasFragmentBinding? = null
    private val binding get() = _binding!!

    //viewModel
    private lateinit var viewModel: PlayasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayasFragmentBinding.inflate(inflater, container, false)

tipo()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun tipo() {
        when (ClickTurismo.tipo) {

            patrimonios ->
                lifecycleScope.launch {
                    binding.listadoPlayas.adapter = PatrimonioAdapter(FirebaseBD().getlistPatrimonio())

                    binding.listadoPlayas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                }
            Playas ->
                lifecycleScope.launch {
                    binding.listadoPlayas.adapter =
                        PlayaAdapter(FirebaseBD().getlistPlayas())

                    binding.listadoPlayas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                }
            Naturaleza ->

                lifecycleScope.launch {
                    binding.listadoPlayas.adapter =
                        PlayaAdapter(FirebaseBD().getlistNaturaleza())

                    binding.listadoPlayas.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                }

        }
    }


}