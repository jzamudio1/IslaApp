package com.jzamudio.isla21410.Playas.listPlayas.Urbasur

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PlayaUrbasurFragment : Fragment() {

    companion object {
        fun newInstance() = PlayaUrbasurFragment()
    }

    private lateinit var viewModel: PlayaUrbasurViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_urbasur_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaUrbasurViewModel::class.java)
        // TODO: Use the ViewModel
    }

}