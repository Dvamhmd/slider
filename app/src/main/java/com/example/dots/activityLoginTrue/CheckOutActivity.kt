package com.example.dots.activityLoginTrue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dots.R
import com.example.dots.adapter.CheckoutAdapter
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.network.ApiClient
import com.example.dots.repository.CheckoutRepository
import com.example.dots.viewmodel.CheckOutViewModel
import com.example.dots.viewmodel.factory.CheckOutViewModelFactory
import com.google.gson.Gson
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.TokenManager
import com.example.dots.utilities.toRupiah
import com.google.gson.reflect.TypeToken

class CheckOutActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CheckoutAdapter
    private lateinit var viewModel: CheckOutViewModel
    private lateinit var loadingView: View
    private var tokoId = ""
    private var items: List<RequestCheckoutItem> = emptyList()

    private val orderTypeLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Update tampilan pengiriman dan toko
            findViewById<TextView>(R.id.deliveryOption).text = when (TokenManager.getDeliveryOption()) {
                "delivery" -> "Delivery"
                "pickup" -> "Pick Up"
                else -> "Pilih Opsi Pengiriman"
            }

            findViewById<TextView>(R.id.StoreName).text = when (TokenManager.getSelectedStore()) {
                "T001" -> "Teh Idaman Concat"
                "T002" -> "Teh Idaman Gejayan"
                "T003" -> "Teh Idaman Wonosari"
                else -> "Toko"
            }

            // Refresh checkout data dari server
            tokoId = TokenManager.getSelectedStore() ?: "T001"
            viewModel.prepareCheckout(tokoId, items)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        recyclerView = findViewById(R.id.orderItemsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CheckoutAdapter(emptyList())
        recyclerView.adapter = adapter


        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        val checkOut = findViewById<Button>(R.id.check_out)

        val repository = CheckoutRepository(ApiClient.getApiService(this))
        val factory = CheckOutViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[CheckOutViewModel::class.java]

        loadingView = findViewById(R.id.loadingOverlay)


        val source = intent.getStringExtra("SOURCE")
        tokoId = TokenManager.getSelectedStore() ?: "T001"
        Log.println(Log.DEBUG, "source", source!!)
        items = if (source == "PRODUK") {
              val idProduk = intent.getStringExtra("ID_PRODUK")!!
              val jumlah = intent.getIntExtra("JUMLAH", 1)
              listOf(RequestCheckoutItem(idProduk, jumlah))
        } else {
              val tempItems = getItemsFromKeranjang()
              if (tempItems.isEmpty()) {
                    finish()
                  }
              tempItems
        }

        checkOut.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
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


    @SuppressLint("SetTextI18n")
    private fun showCheckoutData(data: CheckoutData) {
        findViewById<TextView>(R.id.userName).text = data.alamat_pengguna.namaPenerima
        findViewById<TextView>(R.id.userAddress).text = data.alamat_pengguna.alamat
        findViewById<TextView>(R.id.paymentTotal).text = data.ringkasan_harga.total_akhir.toString().toRupiah()
        findViewById<TextView>(R.id.StoreName).text = when (data.toko_terpilih.id_toko) {
            "T001" -> "Teh Idaman Concat"
            "T002" -> "Teh Idaman Gejayan"
            "T003" -> "Teh Idaman Wonosari"
            else -> "Toko"
        }
        findViewById<CardView>(R.id.deliveryOptionLayout).setOnClickListener {
            val intent = Intent(this, OrderTypeActivity::class.java)
            orderTypeLauncher.launch(intent)
        }



        adapter.updateList(data.items)  // ini akan menampilkan produk
        Log.d("CheckOutActivity line 168", "adapter count: ${adapter.itemCount}")

        Log.d("CheckOutActivity", "produk_dibeli: ${Gson().toJson(data.items)}")
    }


    override fun onResume() {
        super.onResume()

        findViewById<TextView>(R.id.deliveryOption).text = when (TokenManager.getDeliveryOption()) {
            "delivery" -> "Delivery"
            "pickup" -> "Pick Up"
            else -> "Pilih Opsi Pengiriman"
        }

        findViewById<TextView>(R.id.StoreName).text = when (TokenManager.getSelectedStore()) {
            "T001" -> "Teh Idaman Concat"
            "T002" -> "Teh Idaman Gejayan"
            "T003" -> "Teh Idaman Wonosari"
            else -> "Toko"
        }
        observeLiveData()
    }
}
