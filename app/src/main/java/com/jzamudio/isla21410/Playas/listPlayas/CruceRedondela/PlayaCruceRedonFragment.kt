package com.jzamudio.isla21410.Playas.listPlayas.CruceRedondela

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class PlayaCruceRedonFragment : Fragment() {

    companion object {
        fun newInstance() = PlayaCruceRedonFragment()
    }

    private lateinit var viewModel: PlayaCruceRedonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.playa_cruce_redon_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaCruceRedonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}