package com.jzamudio.isla21410.patrimonio.edificios.sanFrancisco

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PatioSanFranciscoFragment : Fragment() {

    companion object {
        fun newInstance() = PatioSanFranciscoFragment()
    }

    private lateinit var viewModel: PatioSanFranciscoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patio_san_francisco_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatioSanFranciscoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}