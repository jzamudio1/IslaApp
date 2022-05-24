package com.jzamudio.isla21410.Principal.turismo.Playas.listPlayas.Camping

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jzamudio.isla21410.databinding.DetailPlayaFragmentBinding
import com.squareup.picasso.Picasso

class PlayaDetailFragment : Fragment(), OnMapReadyCallback {

    //Necesita un MapView
    var mapView: MapView? = null
    var longitud = 0.0
    var latitud = 0.0

    //Sobreescribe la funcion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
    //Binding
    private var _binding: DetailPlayaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PlayaDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = PlayaDetailFragmentArgs.fromBundle(requireArguments())
        longitud = args.longitud.toDouble()
         latitud = args.latitud.toDouble()
        _binding = DetailPlayaFragmentBinding.inflate(inflater,container,false)

        Picasso.get().load(Uri.parse(args.foto)).into(binding.imgPlaya)
        binding.txtDescripcion.text = args.descripcion

        // Gets the MapView from the XML layout and creates it
        mapView = binding.map
        // Gets the MapView from the XML layout and creates it
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this) //this is important


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaDetailViewModel::class.java)
        // TODO: Use the ViewModel
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

        val coordinates = LatLng(latitud,longitud)
        googleMap.addMarker(MarkerOptions().position(coordinates).title("Marker"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14.5f),null)


    }

}