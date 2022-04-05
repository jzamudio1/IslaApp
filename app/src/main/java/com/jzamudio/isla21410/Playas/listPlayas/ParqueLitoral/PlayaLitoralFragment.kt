package com.jzamudio.isla21410.Playas.listPlayas.ParqueLitoral

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PlayaLitoralFragment : Fragment() {

    companion object {
        fun newInstance() = PlayaLitoralFragment()
    }

    private lateinit var viewModel: PlayaLitoralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_litoral_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaLitoralViewModel::class.java)
        // TODO: Use the ViewModel
    }

}