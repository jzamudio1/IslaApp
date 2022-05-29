package com.jzamudio.isla21410.Principal.turismo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentPatrimonioListBinding
import kotlinx.coroutines.launch


class PatrimonioListFragment : Fragment() {
    private var _binding: FragmentPatrimonioListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatrimonioListBinding.inflate(inflater, container, false)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.reciclerPatrimonio.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            binding.reciclerPatrimonio.adapter = InicioAdapter(FirebaseBD().getlistSimpleNameEmpresas())
        }


        return binding.root
    }

}