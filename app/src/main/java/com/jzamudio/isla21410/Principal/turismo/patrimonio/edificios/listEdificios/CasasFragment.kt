package com.jzamudio.isla21410.Principal.turismo.patrimonio.edificios.listEdificios

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.PatrimonioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.CasasFragmentBinding
import kotlinx.coroutines.launch

class CasasFragment : Fragment() {

    //Binding
    private var _binding: CasasFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CasaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CasasFragmentBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            binding.reciclerCasas.adapter = PatrimonioAdapter(FirebaseBD().getlistPatrimonio())
            binding.reciclerCasas.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CasaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}