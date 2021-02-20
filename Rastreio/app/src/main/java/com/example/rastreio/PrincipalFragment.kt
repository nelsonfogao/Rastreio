package com.example.rastreio

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_principal.*


class PrincipalFragment : Fragment(), LocationListener {

    private lateinit var mainViewModel: MainViewModel
    val COARSE_REQUEST = 12345
    val FINE_REQUEST = 54321

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_principal, container, false)
        val listCarroViewModelFactory = MainViewModelFactory()
        mainViewModel = ViewModelProvider(this, listCarroViewModelFactory)
            .get(mainViewModel::class.java)

        getLocationByNetwork()
        getLocationByGps()

        return view
    }

    private fun getLocationByNetwork() {
        var location: Location? = null
        val locationManager =
            requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        val isNetEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isNetEnabled) {
            if (requireContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    2000L,
                    0f,
                    this
                )
                location =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                textViewLatitudeResultado.setText(location?.latitude.toString())
                textViewLongitudeResultado.setText(location?.longitude.toString())
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    COARSE_REQUEST
                )
            }
        }
    }

    private fun getLocationByGps() {
        var location: Location? = null
        val locationManager =
            requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnabled) {
            if (requireContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    2000L,
                    0f,
                    this
                )
                location =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                textViewLatitudeResultado.setText(location?.latitude.toString())
                textViewLongitudeResultado.setText(location?.longitude.toString())
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_REQUEST)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == COARSE_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            getLocationByNetwork()
        if (requestCode == FINE_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            getLocationByGps()
    }

    override fun onLocationChanged(location: Location) {
        TODO("Not yet implemented")
    }


}