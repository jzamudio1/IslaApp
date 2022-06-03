package com.jzamudio.isla21410.Principal.configuration

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.jzamudio.isla21410.Principal.empresa.EmpresaEditFragment
import com.jzamudio.isla21410.databinding.FragmentConfigurateBinding


class ConfigurateFragment : Fragment() {
    private var _binding: FragmentConfigurateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ConfigurateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurateBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ConfigurateViewModel::class.java]

        viewModel.usuario.observe(viewLifecycleOwner) {
            if (it != null)
                binding.txtUser.text = it
        }


        onAdapter()
        editar()
        viewModel.cargarEmpresas()
        borrarItem()
        onVisibleProgressBar()
        binding.btnCerrarSesion.setOnClickListener {
            singOut()
        }

        return binding.root
    }


    fun onAdapter() {
        binding.reciclerEmpresasUser.adapter = viewModel.adaptador
        binding.reciclerEmpresasUser.layoutManager =
            GridLayoutManager(requireContext(), GridLayoutManager.VERTICAL)

    }

    fun editar() {
        viewModel.adaptador.editar.observe(viewLifecycleOwner) {
            if (it != null) {
                EmpresaEditFragment(it, viewModel).show(childFragmentManager, "editar")
            }


        }
    }

    fun borrarItem() {
        viewModel.adaptador.borrar.observe(viewLifecycleOwner) {
            if (it != null) {
                showDialogAlertSimple()
            }

        }

    }


    fun showDialogAlertSimple() {
        AlertDialog.Builder(context)
            .setTitle("Titulo del diálogo")
            .setMessage("Contenido del diálogo.")
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                    viewModel.delete()
                })
            .setNegativeButton(android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón cancel pulsado
                })
            .show()
    }

    fun singOut() {
        FirebaseAuth.getInstance().signOut()
        requireActivity().finishAffinity()
    }


    fun onVisibleProgressBar() {
        viewModel.flagProgres.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

    }

}

