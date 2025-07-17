package com.example.dots.activityLoginTrue

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dots.R
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.network.ApiClient
import com.example.dots.repository.CheckoutRepository
import com.example.dots.viewmodel.CheckOutViewModel
import com.example.dots.viewmodel.factory.CheckOutViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CheckOutActivity : AppCompatActivity() {

    private lateinit var viewModel: CheckOutViewModel
    private lateinit var loadingView: View
    private var tokoId = ""
    private var items: List<RequestCheckoutItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val repository = CheckoutRepository(ApiClient.getApiService(this)) // atau masukkan API service kalau pakai Retrofit
        val factory = CheckOutViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CheckOutViewModel::class.java]

        loadingView = findViewById(R.id.loadingOverlay)

        val source = intent.getStringExtra("SOURCE")
        tokoId = intent.getStringExtra("id_toko") ?: "T001"
        Log.println(Log.DEBUG, "source", source!!)
        // 1. Siapkan items berdasarkan sumber
        items = if (source == "PRODUK") {
              val idProduk = intent.getStringExtra("ID_PRODUK")!!
              val jumlah = intent.getIntExtra("JUMLAH", 1)
              listOf(RequestCheckoutItem(idProduk, jumlah))
        } else {
              val tempItems = getItemsFromKeranjang()
              if (tempItems.isEmpty()) {
                    finish() // keluar dari activity jika item keranjang kosong
                  }
              tempItems
        }


        // 2. Panggil prepareCheckout
        viewModel.prepareCheckout(tokoId, items)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.loading.observe(this) {
            loadingView.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.checkoutResponse.observe(this) { data ->
            showCheckoutData(data!!)
        }

        viewModel.error.observe(this) {
            it?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        }
    }

    private fun getItemsFromKeranjang(): List<RequestCheckoutItem> {
          val keranjangItemsJson = getSharedPreferences("cart", MODE_PRIVATE)
            .getString("keranjang_items", null)

          return if (!keranjangItemsJson.isNullOrEmpty()) {
                Gson().fromJson(keranjangItemsJson, object : TypeToken<List<RequestCheckoutItem>>() {}.type)
              } else {
                Toast.makeText(this, "Data keranjang kosong atau tidak ditemukan", Toast.LENGTH_SHORT).show()
                emptyList()
              }
    }


    private fun showCheckoutData(data: CheckoutData) {
        findViewById<TextView>(R.id.userName).text = data.alamat_pengguna.namaPenerima
        findViewById<TextView>(R.id.userAddress).text = data.alamat_pengguna.alamat
        findViewById<TextView>(R.id.paymentTotal).text = "Rp${data.ringkasan_harga.total_akhir}"
        findViewById<TextView>(R.id.StoreName).text = when (data.toko_terpilih.id_toko) {
            "T001" -> "Teh Idaman Concat"
            "T002" -> "Teh Idaman Gejayan"
            "T003" -> "Teh Idaman Wonosari"
            else -> "Toko"
        }

        // Tampilkan produk di orderDetail1/2 dst sesuai kebutuhan layout
        // ...
    }
}
