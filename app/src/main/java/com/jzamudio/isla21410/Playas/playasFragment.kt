package com.jzamudio.isla21410.Playas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.databinding.PlayasFragmentBinding
import com.squareup.picasso.Picasso

class playasFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    val  storage = Firebase.storage
    val storageRef = storage.reference

    // Create a reference with an initial file path and name
    val pathReference = storageRef.child("images/stars.jpg")
  val mstorage = FirebaseStorage.getInstance().getReference()
    //Binding
    private var _binding: PlayasFragmentBinding? = null
    private val binding get() = _binding!!

    //viewModel
    private lateinit var viewModel: PlayasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayasFragmentBinding.inflate(inflater, container, false)

        //PlayaCentral
        db.collection("images/playa/playaCentral").document("PlayaCentral").get().addOnSuccessListener {
         binding.txtPlayaCentral.setText(it.get("txtPlayaCentral") as String?)
     }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fplayacentral.jpg?alt=media&token=d5188531-223d-4bdd-ba7b-84a445dafc7c")
            .into(binding.imgPlayaCentral);
        //Playa Caiman
        db.collection("images/playa/playaCentral").document("caiman").get().addOnSuccessListener {
            binding.txtPlayaGaviota.setText(it.get("caiman") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fpuntadelcaiman.jpg?alt=media&token=ed7e1f01-7176-4ea7-9e02-76f4ede1470c")
            .into(binding.imgPlayaGaviota);
        //Playa Casita Azul
        db.collection("images/playa/playaCentral").document("casitaazul").get().addOnSuccessListener {
            binding.txtPlayaCasitaAzul.setText(it.get("azul") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fcasitaazul.jpg?alt=media&token=6c50d2ef-e904-432d-9722-ceb7940cbef9")
            .into(binding.imgPlayaCasitaAzul);
        //Playa Camping
        db.collection("images/playa/playaCentral").document("camping").get().addOnSuccessListener {
            binding.txtPlayaCamping.setText(it.get("camping") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fplayacamping.jpg?alt=media&token=318b559f-01e3-4fbb-9ce0-7857ce20f14d")
            .into(binding.imgPlayaCamping);
        //Playa Hoyo
        db.collection("images/playa/playaCentral").document("hoyo").get().addOnSuccessListener {
            binding.txtPlayaHoyo.setText(it.get("hoyo") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fplayadelhoyo.jpg?alt=media&token=cd8db8d1-a2e4-4dde-8772-518c4990ab4a")
            .into(binding.imgPlayaHoyo);

        //Playa Islantilla
        db.collection("images/playa/playaCentral").document("islantilla").get().addOnSuccessListener {
            binding.txtPlayaIslantilla.setText(it.get("Islantilla") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Famanecer_islantilla_1110x624.jpg?alt=media&token=aeb15131-54cf-44ab-b251-5751aeef61d0")
            .into(binding.imgPlayaIslantilla);
        //Playa Parque Litoral
        db.collection("images/playa/playaCentral").document("parquelitoral").get().addOnSuccessListener {
            binding.txtPlayaRedondela.setText(it.get("parquelitoral") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fparquelitoral.jpg?alt=media&token=1c8a22f2-bb91-463c-bf13-a7ff2796de93")
            .into(binding.imgPlayaRedondela);
        //Playa Pesmar
        db.collection("images/playa/playaCentral").document("pesmar").get().addOnSuccessListener {
            binding.txtPlayaPesmar.setText(it.get("pesmar") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fpesmar.jpg?alt=media&token=e08c4433-82d2-47ee-880d-8a99b2804579")
            .into(binding.imgPlayaPesmar);
        //Playa Santana
        db.collection("images/playa/playaCentral").document("santana").get().addOnSuccessListener {
            binding.txtPlayaSantana.setText(it.get("santana") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fplayasantana.jpg?alt=media&token=32e6486f-3496-4489-a81e-2ad80ca22090")
            .into(binding.imgPlayaSantana);
        //Playa Urbasur
        db.collection("images/playa/playaCentral").document("urbasur").get().addOnSuccessListener {
            binding.txtPlayaUrbasur.setText(it.get("urbasur") as String?)
        }
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/islaapp-346314.appspot.com/o/playas%2Fplayaurbasur_1.jpg?alt=media&token=1bbcdb7c-d792-4740-9803-310c53eb6b24")
            .into(binding.imgPlayaUrbasur);

        binding.btnPlayaCentral.setOnClickListener {
            findNavController().navigate(R.id.action_playasFragment_to_playaCentralFragment)
        }

        binding.btnCamping.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaCampingFragment)
        }
        binding.btnCasitaAzul.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaCasitaAzulFragment)
        }
        binding.btnGaviota.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaCaimanFragment)
        }
        binding.btnHoyo.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaHoyoFragment)
        }
        binding.btnIslantilla.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_islantillaFragment)
        }
        binding.btnPesmar.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaPesmarFragment)
        }
        binding.btnRedondela.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaCruceRedonFragment)
        }
        binding.btnUrbasur.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaUrbasurFragment)
        }
        binding.btnSantana.setOnClickListener {
            findNavController().navigate(R.id.action_playassd_to_playaLitoralFragment)
        }








        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}