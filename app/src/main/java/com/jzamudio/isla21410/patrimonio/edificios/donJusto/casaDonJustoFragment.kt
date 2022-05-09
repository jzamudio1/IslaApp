package com.jzamudio.isla21410.patrimonio.edificios.donJusto

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class casaDonJustoFragment : Fragment() {

    companion object {
        fun newInstance() = casaDonJustoFragment()
    }

    private lateinit var viewModel: CasaDonJustoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.casa_don_justo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CasaDonJustoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}