package com.example.dots.activityLoginTrue

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.dots.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class PickLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    private lateinit var fused: FusedLocationProviderClient
    private lateinit var geocoder: Geocoder
    private lateinit var addressTv: TextView
    private var lastAddress: String? = null
    private var lastLatLng: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_location)

        addressTv = findViewById(R.id.address_text)
        geocoder = Geocoder(this, Locale.getDefault())
        fused = LocationServices.getFusedLocationProviderClient(this)

        (supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment)
            .getMapAsync(this)

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            if (lastAddress != null && lastLatLng != null) {
                val data = Intent().apply {
                    putExtra("alamat", lastAddress)
                    putExtra("lat", lastLatLng!!.latitude)
                    putExtra("lng", lastLatLng!!.longitude)
                }
                setResult(RESULT_OK, data)
                finish()
            } else {
                Toast.makeText(this, "Alamat belum siap", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        gMap = map
        gMap.uiSettings.isMyLocationButtonEnabled = true

        if (checkLocationPermission()) {
            gMap.isMyLocationEnabled = true
            fused.lastLocation.addOnSuccessListener { loc ->
                loc?.let {
                    val here = LatLng(it.latitude, it.longitude)
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 17f))
                    reverseGeocode(here)
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
        }

        // Trigger setiap kamera berhenti
        gMap.setOnCameraIdleListener {
            val center = gMap.cameraPosition.target
            reverseGeocode(center)
        }
    }

    private fun reverseGeocode(latLng: LatLng) {
        try {
            val list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            val addrLine = list?.firstOrNull()?.getAddressLine(0)
            addressTv.text = addrLine ?: "Alamat tidak ditemukan"
            lastAddress = addrLine
            lastLatLng = latLng
        } catch (e: Exception) {
            addressTv.text = "Gagal geocoder"
            lastAddress = null
        }
    }

    private fun checkLocationPermission(): Boolean =
        ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            recreate() // Refresh activity untuk aktifkan myLocation
        } else {
            Toast.makeText(this, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }
}
