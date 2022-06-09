package com.jzamudio.isla21410.Principal.configuration

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
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
import com.squareup.picasso.Picasso

/**
 * Clase de configuracion para el usuario
 */
class ConfigurateFragment : Fragment() {
    //Binding
    private var _binding: FragmentConfigurateBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private lateinit var viewModel: ConfigurateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurateBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ConfigurateViewModel::class.java]

        viewModel.foto.observe(viewLifecycleOwner) {
            if (it != null)
                Picasso.get().load(Uri.parse(it)).into(binding.imgUser)
        }

        //Observa el Nombre del usuario
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

    /**
     * Metodo que carga el adaptador
     */
    fun onAdapter() {
        binding.reciclerEmpresasUser.adapter = viewModel.adaptador
        binding.reciclerEmpresasUser.layoutManager =
            GridLayoutManager(requireContext(), GridLayoutManager.VERTICAL)

    }

    /**
     * Metodo que observa el livedata y si no es nulo, llama a EmpresaEdit que es un DialogFragment
     */
    private fun editar() {
        viewModel.adaptador.editar.observe(viewLifecycleOwner) {
            if (it != null) {
                EmpresaEditFragment(it, viewModel).show(childFragmentManager, "editar")
            }


        }
    }

    /**
     * Metodo que observa el livedata y si no es nulo, llama al AlertDialog
     */
    private fun borrarItem() {
        viewModel.adaptador.borrar.observe(viewLifecycleOwner) {
            if (it != null) {
                showDialogAlertSimple()
            }

        }

    }


    /**
     * Metodo que contiene un AlertDialog para borrar la empresa.
     */
    private fun showDialogAlertSimple() {
        AlertDialog.Builder(context)
            .setTitle("CUIDADO!!!")
            .setMessage("VAS A BORRAR SU EMPRESA!!")
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

    /**
     * Metodo que cierra sesion de la App
     */
    private fun singOut() {
        FirebaseAuth.getInstance().signOut()
        requireActivity().finishAffinity()
    }

    /**
     * Metodo que establece el progresBar
     */
    private fun onVisibleProgressBar() {
        viewModel.flagProgres.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

    }

}

