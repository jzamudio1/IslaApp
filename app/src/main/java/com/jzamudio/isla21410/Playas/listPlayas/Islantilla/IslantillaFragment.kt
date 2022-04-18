package com.jzamudio.isla21410.Playas.listPlayas.Islantilla

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
import com.jzamudio.isla21410.R
import com.jzamudio.isla21410.databinding.IslantillaFragmentBinding
import com.jzamudio.isla21410.databinding.PlayaCentralFragmentBinding

class IslantillaFragment : Fragment(), OnMapReadyCallback {

    //Necesita un MapView
    var mapView: MapView? = null

    //Sobreescribe la funcion
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
    //Binding
    private var _binding: IslantillaFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IslantillaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IslantillaFragmentBinding.inflate(inflater,container,false)
        // Gets the MapView from the XML layout and creates it
        mapView = binding.map
        // Gets the MapView from the XML layout and creates it
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this) //this is important
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IslantillaViewModel::class.java)
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
        val coordinates = LatLng(37.195787, -7.305554)
        //googleMap.getUiSettings()?.setZoomControlsEnabled(true);
        // googleMap.addMarker(MarkerOptions().position(coordinates))
        googleMap.addMarker(MarkerOptions().position(coordinates).title("Marker"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14.5f),4000,null)


    }

}