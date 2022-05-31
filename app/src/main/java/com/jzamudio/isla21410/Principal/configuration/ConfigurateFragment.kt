package com.jzamudio.isla21410.Principal.configuration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.jzamudio.isla21410.Principal.empresa.EmpresaEditFragment
import com.jzamudio.isla21410.adapter.EmpresaUserAdapter
import com.jzamudio.isla21410.adapter.EmpresaUserViewHolder
import com.jzamudio.isla21410.adapter.InicioAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.database.model.ComentUser
import com.jzamudio.isla21410.database.model.SimpleName
import com.jzamudio.isla21410.databinding.FragmentConfigurateBinding
import kotlinx.coroutines.launch


class ConfigurateFragment : Fragment() {

    private var _binding: FragmentConfigurateBinding? = null
    private val binding get() = _binding!!
    private lateinit var usuario: String
    val listInit = mutableListOf<ComentUser>()
    private lateinit var adaptador: EmpresaUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfigurateBinding.inflate(inflater, container, false)

        binding.btnCerrarSesion.setOnClickListener {
            sinOut()
        }

        adaptador = EmpresaUserAdapter(listInit)
        binding.reciclerEmpresasUser.adapter = adaptador
        binding.reciclerEmpresasUser.layoutManager = GridLayoutManager(requireContext(),GridLayoutManager.VERTICAL)

        cargarEmpresas()

        editar()

        user()
        return binding.root
    }


    fun cargarEmpresas(){
        lifecycleScope.launch {
            val id= FirebaseBD().getListIdEmpresa()
            Log.i("idEmpresa", id.toString())
            lifecycleScope.launch{
                FirebaseBD().getListDocEmpresa(id).forEach {
                    listInit.add(0, it)
                    adaptador.notifyItemInserted(0)
                    Log.i("idEmpresa", "Adapter notificado")
                }
            }.join()
            Log.i("idEmpresa", listInit.toString())
        }
    }


    private fun sinOut() {
        requireActivity().finishAffinity()
        FirebaseAuth.getInstance().signOut()

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
           if(it != null){
               EmpresaEditFragment(it).show(childFragmentManager,"editar")
           }


        }

    }
    //

    fun mostrarComentUser() {
        lifecycleScope.launch {
            FirebaseBD().getlistSimpleNameTurismo()
        }

    }

}