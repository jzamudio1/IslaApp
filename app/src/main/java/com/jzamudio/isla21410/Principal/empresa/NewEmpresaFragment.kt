package com.jzamudio.isla21410.Principal.empresa

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jzamudio.isla21410.databinding.FragmentNewEmpresaBinding

class NewEmpresaFragment : Fragment() {


    private var _binding: FragmentNewEmpresaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewEmpresaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewEmpresaBinding.inflate(inflater, container, false)







        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewEmpresaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}