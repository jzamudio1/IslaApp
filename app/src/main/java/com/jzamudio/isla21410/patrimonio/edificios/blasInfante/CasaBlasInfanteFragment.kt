package com.jzamudio.isla21410.patrimonio.edificios.blasInfante

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class CasaBlasInfanteFragment : Fragment() {

    companion object {
        fun newInstance() = CasaBlasInfanteFragment()
    }

    private lateinit var viewModel: CasaBlasInfanteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.casa_blas_infante_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CasaBlasInfanteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}