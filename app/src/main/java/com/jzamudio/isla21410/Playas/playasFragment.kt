package com.jzamudio.isla21410.Playas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.PlayaAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.playas_fragment.*
import kotlinx.coroutines.launch

class playasFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    val  storage = Firebase.storage
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

        lifecycleScope.launch {
            binding.listadoPlayas.adapter = PlayaAdapter(FirebaseBD().getlistPlayas())
            binding.listadoPlayas.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}