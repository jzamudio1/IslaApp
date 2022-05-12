package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.Principal.turismo.Playas.listPlayas.Camping.PlayaCampingFragmentArgs
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.adapter.EmpresaInitAdapter
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentEmpresasBinding
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import kotlinx.coroutines.launch

class listEmpresaFragment : Fragment() {


    private var _binding: FragmentListEmpresaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListEmpresaBinding.inflate(inflater, container, false)

        val args = listEmpresaFragmentArgs.fromBundle(requireArguments())

        lifecycleScope.launch {
            binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistEmpresas())
            binding.btnEmpresas.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

}