package com.jzamudio.isla21410.Playas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding

class playasFragment : Fragment() {

    //Binding
    private var _binding: PlayasFragmentBinding? = null
    private val binding get() = _binding!!
    //viewModel
    private lateinit var viewModel: PlayasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayasFragmentBinding.inflate(inflater,container,false)







        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}