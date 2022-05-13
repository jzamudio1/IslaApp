package com.jzamudio.isla21410.Principal.empresa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.FragmentListEmpresaBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.Alojamiento
import com.jzamudio.isla21410.util.ClickEmpresas.Companion.Servicio
import kotlinx.coroutines.launch

class listEmpresaFragment : Fragment() {


    private var _binding: FragmentListEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var empresaVM: listEmpresaFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListEmpresaBinding.inflate(inflater, container, false)

        //val args = listEmpresaFragmentArgs.fromBundle(requireArguments())


        tipo()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        empresaVM = ViewModelProvider(this).get(listEmpresaFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }


    fun tipo(){
        when(ClickEmpresas.tipo){
            Servicio->
                lifecycleScope.launch {
                    binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistEmpresasServicio())


                    binding.btnEmpresas.layoutManager=
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                    //ClickListner.Servicio=false
                }
           Alojamiento->
                lifecycleScope.launch {
                    binding.btnEmpresas.adapter = EmpresasAdapter(FirebaseBD().getlistEmpresasAlojamiento())


                    binding.btnEmpresas.layoutManager=
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                    //ClickListner.Servicio=false
                }


        }
    }

}