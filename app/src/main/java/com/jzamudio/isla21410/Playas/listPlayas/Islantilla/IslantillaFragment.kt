package com.jzamudio.isla21410.Playas.listPlayas.Islantilla

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jzamudio.isla21410.R

class IslantillaFragment : Fragment() {

    companion object {
        fun newInstance() = IslantillaFragment()
    }

    private lateinit var viewModel: IslantillaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.islantilla_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IslantillaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}