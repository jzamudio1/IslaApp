package com.jzamudio.isla21410.Playas.listPlayas.CasitaAzul

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class playaCasitaAzulFragment : Fragment() {

    companion object {
        fun newInstance() = playaCasitaAzulFragment()
    }

    private lateinit var viewModel: PlayaCasitaAzulViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_casita_azul_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaCasitaAzulViewModel::class.java)
        // TODO: Use the ViewModel
    }

}