package com.example.googlmapschacker.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.googlmapschacker.Map
import com.example.googlmapschacker.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(R.layout.fragment_maps) {


    private var map: GoogleMap? = null
    private var callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        val position = (requireActivity() as Map).mapPosition

        googleMap.apply {
            addMarker(MarkerOptions().position(position).title("Marker in Sydney"))
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
//            setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
//                override fun getInfoWindow(p0: Marker?): View {
//                    return View(requireContext())
//                }
//
//                override fun getInfoContents(p0: Marker?): View {
//                }
//            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

//    fun setPosition(latLng: LatLng){
//        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
//        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
//    }
}