package com.jzamudio.isla21410.Principal.turismo.Playas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.jzamudio.isla21410.adapter.EmpresasAdapter
import com.jzamudio.isla21410.adapter.TurismoAdapter
import com.jzamudio.isla21410.adapter.PlayaAdapter
import com.jzamudio.isla21410.database.conexion.FirebaseBD
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import com.jzamudio.isla21410.util.ClickEmpresas
import com.jzamudio.isla21410.util.ClickTurismo
import com.jzamudio.isla21410.util.ClickTurismo.Companion.Naturaleza
import com.jzamudio.isla21410.util.ClickTurismo.Companion.Patrimonio
import com.jzamudio.isla21410.util.ClickTurismo.Companion.Playas
import kotlinx.coroutines.launch

class playasFragment : Fragment() {

    //Binding
    private var _binding: PlayasFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayasFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }






}