package com.jzamudio.isla21410.Playas.listPlayas.PuntaCaiman

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PlayaCaimanFragment : Fragment() {

    companion object {
        fun newInstance() = PlayaCaimanFragment()
    }

    private lateinit var viewModel: PlayaCaimanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_caiman_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaCaimanViewModel::class.java)
        // TODO: Use the ViewModel
    }

}