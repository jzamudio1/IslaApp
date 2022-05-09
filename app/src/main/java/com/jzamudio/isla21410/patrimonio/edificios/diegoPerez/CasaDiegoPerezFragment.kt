package com.jzamudio.isla21410.patrimonio.edificios.diegoPerez

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class CasaDiegoPerezFragment : Fragment() {

    companion object {
        fun newInstance() = CasaDiegoPerezFragment()
    }

    private lateinit var viewModel: CasaDiegoPerezViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.casa_diego_perez_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CasaDiegoPerezViewModel::class.java)
        // TODO: Use the ViewModel
    }

}