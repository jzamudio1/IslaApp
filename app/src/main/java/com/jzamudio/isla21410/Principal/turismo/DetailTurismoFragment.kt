package com.jzamudio.isla21410.Principal.turismo

import android.net.Uri
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
import com.jzamudio.isla21410.databinding.FragmentDetailTurismoBinding
import com.squareup.picasso.Picasso

class DetailTurismoFragment : Fragment(), OnMapReadyCallback {
    //Necesita un MapView
    private var mapView: MapView? = null
    private var longitud = 0.0
    private var latitud = 0.0

    //Sobreescribe la funcion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    private var _binding: FragmentDetailTurismoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailTurismoBinding.inflate(inflater, container, false)
        val args = DetailTurismoFragmentArgs.fromBundle(requireArguments())
        longitud = args.longitud.toDouble()
        latitud = args.latitud.toDouble()


        Picasso.get().load(Uri.parse(args.foto)).into(binding.imgPlaya)
        binding.txtDescripcion.text = args.descripcion

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

        val coordinates = LatLng(latitud,longitud)
        googleMap.addMarker(MarkerOptions().position(coordinates).title("Marker"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14.5f),null)


    }

}