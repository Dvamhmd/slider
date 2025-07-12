package com.example.dots.activityLoginTrue

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateAddressActivity : AppCompatActivity() {

    private lateinit var addressResultTextView: TextView
    private lateinit var mapPreviewCard: MaterialCardView
    private lateinit var mapTitle: TextView
    private lateinit var mapNote: TextView

    private var selectedLatLng: LatLng? = null
    private var selectedAddress: String? = null

    private val pickLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val alamat = result.data?.getStringExtra("alamat")
            val lat = result.data?.getDoubleExtra("lat", 0.0) ?: 0.0
            val lng = result.data?.getDoubleExtra("lng", 0.0) ?: 0.0

            selectedAddress = alamat
            selectedLatLng = LatLng(lat, lng)

            if (alamat != null) {
                addressResultTextView.text = alamat
                Toast.makeText(this, "Alamat dipilih: $alamat", Toast.LENGTH_SHORT).show()
                showMapPreview()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_address)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = true

        addressResultTextView = findViewById(R.id.addressResult)
        mapPreviewCard = findViewById(R.id.map_preview_card)
        mapTitle = findViewById(R.id.titik_lokasi_kamu)
        mapNote = findViewById(R.id.note_text)

        findViewById<ImageView>(R.id.back).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<MaterialCardView>(R.id.choose_location).setOnClickListener {
            val intent = Intent(this, PickLocationActivity::class.java)
            pickLocationLauncher.launch(intent)
        }



        //variabel layout alamat
        val addressDetailLayout = findViewById<TextInputLayout>(R.id.addressDetailLayout)
        val addressLabelLayout = findViewById<TextInputLayout>(R.id.addressLabelLayout)
        val addressNameLayout = findViewById<TextInputLayout>(R.id.addressNameLayout)
        val addressPhoneLayout = findViewById<TextInputLayout>(R.id.addressPhoneLayout)



        //variabel input alamat
        val addressDetailInput = findViewById<TextInputEditText>(R.id.addressDetail)
        val addressLabelInput = findViewById<TextInputEditText>(R.id.addressLabel)
        val addressNameInput = findViewById<TextInputEditText>(R.id.addressName)
        val addressPhoneInput = findViewById<TextInputEditText>(R.id.addressPhone)





        //button simpan alamat
        val saveAddress = findViewById<Button>(R.id.save)

        //navigasi dan kirim variabel
        saveAddress.setOnClickListener {

            //variabel alamat
            val address = addressResultTextView.text.toString()
            val addressDetail = addressDetailInput.text.toString()
            val addressLabel = addressLabelInput.text.toString()
            val addressName = addressNameInput.text.toString()
            val addressPhone = addressPhoneInput.text.toString()

            //error handling
            addressDetailLayout.error = null
            addressLabelLayout.error = null
            addressNameLayout.error = null
            addressPhoneLayout.error = null

            if (address.isEmpty()) {
                Toast.makeText(this, "Pilih lokasi dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (addressDetail.isEmpty()) {
                addressDetailLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            if (addressLabel.isEmpty()) {
                addressLabelLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            if (addressName.isEmpty()) {
                addressNameLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            if (addressPhone.isEmpty()) {
                addressPhoneLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            // Kirim balik data ke FragmentDelivery
            val resultIntent = Intent().apply {
                putExtra("ADDRESS", address)
                putExtra("ADDRESS_DETAIL", addressDetail)
                putExtra("ADDRESS_LABEL", addressLabel)
                putExtra("ADDRESS_NAME", addressName)
                putExtra("ADDRESS_PHONE", addressPhone)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
















    }

    private fun showMapPreview() {
        // Tampilkan card map-nya
        mapPreviewCard.visibility = View.VISIBLE

        // Sembunyikan teks awal
        mapTitle.visibility = View.GONE
        mapNote.visibility = View.GONE

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            googleMap.uiSettings.setAllGesturesEnabled(false)
            googleMap.uiSettings.isMapToolbarEnabled = false
            googleMap.uiSettings.isZoomControlsEnabled = false

            selectedLatLng?.let { latLng ->
                googleMap.clear()
                googleMap.addMarker(MarkerOptions().position(latLng))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }
        }
    }
}
