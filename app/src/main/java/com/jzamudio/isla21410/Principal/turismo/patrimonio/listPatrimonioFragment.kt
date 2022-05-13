package com.jzamudio.isla21410.Principal.turismo.patrimonio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.databinding.ListPatrimonioFragmentBinding


class listPatrimonioFragment : Fragment() {


    //Binding
    private var _binding: ListPatrimonioFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListPatrimonioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListPatrimonioFragmentBinding.inflate(inflater, container, false)



        return binding.root
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListPatrimonioViewModel::class.java)
        // TODO: Use the ViewModel
    }


}