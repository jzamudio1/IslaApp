package com.jzamudio.isla21410.Playas.listPlayas.Camping

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PlayaCampingFragment : Fragment() {

    companion object {
        fun newInstance() = PlayaCampingFragment()
    }

    private lateinit var viewModel: PlayaCampingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_camping_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaCampingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}