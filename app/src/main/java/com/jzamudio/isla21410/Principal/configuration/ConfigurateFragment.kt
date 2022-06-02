package com.jzamudio.isla21410.Principal.configuration

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jzamudio.isla21410.Principal.empresa.EmpresaEditFragment
import com.jzamudio.isla21410.adapter.EmpresaUserAdapter

import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.databinding.FragmentConfigurateBinding

import com.jzamudio.isla21410.util.ClickEmpresas.Companion.flagLogin
import kotlinx.coroutines.launch


class ConfigurateFragment : Fragment() {

    private var _binding: FragmentConfigurateBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuario: String
    val listInit = mutableListOf<ComentUser>()
    lateinit var adaptador: EmpresaUserAdapter
    val rechearAdapter: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurateBinding.inflate(inflater, container, false)

        onAdapter()
        cargarEmpresas()
        editar()
        user()

        binding.btnCerrarSesion.setOnClickListener {
            sinOut()
        }

        return binding.root
    }

    fun delete() {
        lifecycleScope.launch {
            val id = FirebaseBD().getListIdEmpresa()
            lifecycleScope.launch {
                FirebaseBD().borraEmpresa(id)
            }
        }
    }


    fun deleteItem(item: Int) {
        listInit.removeAt(item)
        adaptador.notifyItemRemoved(item)
    }

    fun onAdapter() {
        adaptador = EmpresaUserAdapter(listInit, requireContext(), this, rechearAdapter)
        binding.reciclerEmpresasUser.adapter = adaptador
        binding.reciclerEmpresasUser.layoutManager =
            GridLayoutManager(requireContext(), GridLayoutManager.VERTICAL)

    }

    fun cargarEmpresas() {
        if (rechearAdapter) {
            lifecycleScope.launch {
                val id = FirebaseBD().getListIdEmpresa()
                FirebaseBD().getListDocEmpresa(id).forEach {
                    listInit.add(it)
                    adaptador.notifyItemInserted(listInit.indexOf(it))
                }
            }
        }
    }


    private fun sinOut() {
        requireActivity().finishAffinity()
        FirebaseAuth.getInstance().signOut()
        flagLogin = true

    }


    fun user() {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        FirebaseFirestore.getInstance().collection("users").document(user).get()
            .addOnSuccessListener {
                usuario = it.get("nombre").toString()
                binding.txtUser.text = usuario

            }
    }


    fun editar() {
        adaptador.live.observe(viewLifecycleOwner) {
            if (it != null) {
                EmpresaEditFragment(it, this).show(childFragmentManager, "editar")
            }


        }

    }


    fun rechear() {
        adaptador.notifyItemChanged(listInit.indexOf(adaptador.live.value))
    }

    fun mostrarComentUser() {
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameTurismo()
        }

    }


}