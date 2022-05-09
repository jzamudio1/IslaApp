package com.jzamudio.isla21410.patrimonio.edificios.romanPerez

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class CasaRomanPerezFragment : Fragment() {

    companion object {
        fun newInstance() = CasaRomanPerezFragment()
    }

    private lateinit var viewModel: CasaRomanPerezViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.casa_roman_perez_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CasaRomanPerezViewModel::class.java)
        // TODO: Use the ViewModel
    }

}