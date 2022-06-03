package com.jzamudio.isla21410.Principal.turismo

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jzamudio.isla21410.databinding.FragmentDetailTurismoBinding
import com.squareup.picasso.Picasso

class DetailTurismoFragment : Fragment(), OnMapReadyCallback {
    //Necesita un MapView
    private var _binding: FragmentDetailTurismoBinding? = null
    private val binding get() = _binding!!
    private var mapView: MapView? = null
    private lateinit var viewModel: DetailTurismoViewModel
    private lateinit var viewModelFactory:DetailTurismoViewModel.Factory

    //Sobreescribe la funcion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailTurismoBinding.inflate(inflater, container, false)
        val args = DetailTurismoFragmentArgs.fromBundle(requireArguments())
        viewModelFactory = DetailTurismoViewModel.Factory(args.foto,args.nombre,args.descripcion,args.latitud,args.longitud)
        viewModel = ViewModelProvider(this,viewModelFactory)[DetailTurismoViewModel::class.java]


        Picasso.get().load(Uri.parse(viewModel.foto)).into(binding.imgPlaya)
        binding.txtDescripcion.text = viewModel.descripcion
        binding.txtNombreTurismo.text = viewModel.nombre

        // Gets the MapView from the XML layout and creates it
        mapView = binding.map
        // Gets the MapView from the XML layout and creates it
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this) //this is important

        return binding.root
    }

    //Funciones Necesarias para que funcione el MapView
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val coordinates = LatLng(viewModel.latitud.toDouble(),viewModel.longitud.toDouble())
        googleMap.addMarker(MarkerOptions().position(coordinates).title("Marker"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14.5f),null)


    }

}