package com.jzamudio.isla21410.Principal.turismo.Playas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.adapter.TurismoAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentPlantillaTurismoBinding
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import kotlinx.coroutines.launch

class PlantillaTurismoFragment : Fragment() {


    //Binding
    private var _binding: FragmentPlantillaTurismoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantillaTurismoBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
            binding.recyclerTurismo.adapter = TurismoAdapter(FirebaseBD().getlistTurismo())
            binding.recyclerTurismo.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }


        return binding.root
    }


}