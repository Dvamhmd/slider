package com.example.dots.activityLoginTrue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R
import com.google.android.material.card.MaterialCardView
import android.widget.TextView

class CreateAddressActivity : AppCompatActivity() {

    private lateinit var addressResultTextView: TextView
    private var selectedAddress: String? = null

    private val pickLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val alamat = result.data?.getStringExtra("alamat")
            alamat?.let {
                selectedAddress = it
                Toast.makeText(this, "Alamat dipilih: $alamat", Toast.LENGTH_SHORT).show()
                addressResultTextView.text = it
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

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        addressResultTextView = findViewById(R.id.addressResult)

        val chooseLocation = findViewById<MaterialCardView>(R.id.choose_location)
        chooseLocation.setOnClickListener {
            val intent = Intent(this, PickLocationActivity::class.java)
            pickLocationLauncher.launch(intent)
        }
    }
}
