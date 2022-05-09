package com.jzamudio.isla21410.patrimonio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class listPatrimonioFragment : Fragment() {

    companion object {
        fun newInstance() = listPatrimonioFragment()
    }

    private lateinit var viewModel: ListPatrimonioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_patrimonio_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListPatrimonioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}