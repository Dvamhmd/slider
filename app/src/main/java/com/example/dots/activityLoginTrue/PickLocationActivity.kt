package com.example.dots.activityLoginTrue

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.Locale

@Suppress("DEPRECATION")
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

        // Status bar light mode
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        addressTv = findViewById(R.id.address_text)
        geocoder = Geocoder(this, Locale.getDefault())
        fused = LocationServices.getFusedLocationProviderClient(this)

        // Init map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        // Init Places
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.google_maps_key))
        }

        // Autocomplete Search
        findViewById<View>(R.id.search_bar).setOnClickListener {
            val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)
            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
                .build(this)
            startActivityForResult(intent, 101)
        }

        // Save Location
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
            getLastKnownLocation()
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
        }

        gMap.setOnCameraIdleListener {
            val center = gMap.cameraPosition.target
            reverseGeocode(center)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fused.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val userLatLng = LatLng(location.latitude, location.longitude)
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 17f))
                reverseGeocode(userLatLng)
            } else {
                // fallback to Jakarta
                val fallback = LatLng(-6.200000, 106.816666)
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fallback, 12f))
                reverseGeocode(fallback)
                Toast.makeText(this, "Lokasi tidak tersedia, gunakan default", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Gagal mendapatkan lokasi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun reverseGeocode(latLng: LatLng) {
        try {
            val list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            val addrLine = list?.firstOrNull()?.getAddressLine(0)
            addressTv.text = addrLine ?: "Alamat tidak ditemukan"
            lastAddress = addrLine
            lastLatLng = latLng
        } catch (_: Exception) {
            addressTv.text = getString(R.string.gagal_geocoder)
            lastAddress = null
            lastLatLng = null
        }
    }

    private fun checkLocationPermission(): Boolean =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getLastKnownLocation()
        } else {
            Toast.makeText(this, "Izin lokasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val place = Autocomplete.getPlaceFromIntent(data!!)
            val latLng = place.latLng
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng!!, 17f))
            addressTv.text = place.address
            lastAddress = place.address
            lastLatLng = latLng
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            val status = Autocomplete.getStatusFromIntent(data!!)
            Toast.makeText(this, "Error: ${status.statusMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}
