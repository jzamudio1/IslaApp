package com.jzamudio.isla21410.Principal.turismo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.adapter.TurismoAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.database.model.Turismo
import com.jzamudio.isla21410.databinding.FragmentTurismoListBinding
import kotlinx.coroutines.launch

class TurismoListFragment : Fragment() {
    private var _binding: FragmentTurismoListBinding? = null
    private val binding get() = _binding!!
    val listInit = mutableListOf<Turismo>()
    private lateinit var adaptador: TurismoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTurismoListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment

        onAdapter()

        return binding.root
    }


    private fun onAdapter() {
        listInit.clear()
        adaptador = TurismoAdapter(listInit)
        binding.reciclerTurismo.adapter = adaptador
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.reciclerTurismo.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            listInit.addAll(FirebaseBD().getlistTurismo())
            adaptador.notifyDataSetChanged()
        }
    }
}