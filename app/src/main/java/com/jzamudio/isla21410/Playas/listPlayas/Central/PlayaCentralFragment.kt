package com.jzamudio.isla21410.Playas.listPlayas.Central

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jzamudio.isla21410.databinding.PlayaCentralFragmentBinding


class playaCentralFragment : Fragment(), OnMapReadyCallback {

    var mapView: MapView? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    //Binding
    private var _binding: PlayaCentralFragmentBinding? = null
    private val binding get() = _binding!!
    //viewModel
    private lateinit var viewModel: PlayaCentralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayaCentralFragmentBinding.inflate(inflater,container,false)

// Gets the MapView from the XML layout and creates it
        // Gets the MapView from the XML layout and creates it
        mapView = binding.map
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this) //this is important


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayaCentralViewModel::class.java)
        // TODO: Use the ViewModel
    }


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
        val coordinates = LatLng(37.195787, -7.305554)
        //googleMap.getUiSettings()?.setZoomControlsEnabled(true);
        // googleMap.addMarker(MarkerOptions().position(coordinates))
        googleMap.addMarker(MarkerOptions().position(coordinates).title("Marker"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14.5f),4000,null)


    }

}