package com.example.dots.activityLoginTrue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.TokenManager
import com.example.dots.adapter.CheckoutAdapter
import com.example.dots.models.CheckoutData
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.network.ApiClient
import com.example.dots.repository.CheckoutRepository
import com.example.dots.utilities.toRupiah
import com.example.dots.viewmodel.CheckOutViewModel
import com.example.dots.viewmodel.factory.CheckOutViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CheckOutActivity : AppCompatActivity() {

      private lateinit var recyclerView: RecyclerView
      private lateinit var adapter: CheckoutAdapter
      private lateinit var viewModel: CheckOutViewModel
      private lateinit var loadingView: View
      private lateinit var notes: EditText
      private var tokoId = ""
      private var items: List<RequestCheckoutItem> = emptyList()

      companion object {
            const val SOURCE_PRODUK = "PRODUK"
            const val SOURCE_KERANJANG = "KERANJANG"
          }

      private val orderTypeLauncher = registerForActivityResult(
            androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
      ) { result ->
            if (result.resultCode == RESULT_OK) {
                  updateDeliveryUI()
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

            loadingView = findViewById(R.id.loadingOverlay)
            notes = findViewById(R.id.notesInput)

            findViewById<ImageView>(R.id.back).setOnClickListener {
                  onBackPressedDispatcher.onBackPressed()
                  finish()
                }

            val repository = CheckoutRepository(ApiClient.getApiService(this))
            val factory = CheckOutViewModelFactory(repository)
            viewModel = ViewModelProvider(this, factory)[CheckOutViewModel::class.java]

            val source = intent.getStringExtra("SOURCE") ?: SOURCE_KERANJANG
            tokoId = TokenManager.getSelectedStore() ?: "T001"
            Log.d("CheckOutActivity", "source = $source")

            items = if (source == SOURCE_PRODUK) {
                  val idProduk = intent.getStringExtra("ID_PRODUK")!!
                  val jumlah = intent.getIntExtra("JUMLAH", 1)
                  listOf(RequestCheckoutItem(idProduk, jumlah))
                } else {
                  val tempItems = getItemsFromKeranjang()
                  if (tempItems.isEmpty()) {
                    finish()
                    return
                  }
                  tempItems
                }

            findViewById<Button>(R.id.check_out).setOnClickListener {
                  val metodePengiriman = TokenManager.getDeliveryOption() ?: "delivery"
                val alamatId = viewModel.checkoutResponse.value?.alamat_pengguna?.idAlamat
                if (alamatId.isNullOrEmpty()) {
                      Toast.makeText(this, "Alamat belum tersedia. Silakan pilih atau tambahkan alamat.", Toast.LENGTH_SHORT).show()
                      return@setOnClickListener
                }

                val promoId = null // future use
                  val catatan = notes.text.toString()

                  viewModel.placeOrder(
                    tokoId = tokoId,
                    items = items,
                    metodePengiriman = metodePengiriman,
                    idAlamat = alamatId,
                    promoId = promoId,
                    catatan = catatan
                  )
                }

            viewModel.prepareCheckout(tokoId, items)
            observeLiveData()
            updateDeliveryUI()
          }

      private fun observeLiveData() {
            viewModel.loading.observe(this) {
                  loadingView.visibility = if (it) View.VISIBLE else View.GONE
                }

            viewModel.checkoutResponse.observe(this) { data ->
                  data?.let { showCheckoutData(it) }
                }

            viewModel.error.observe(this) { errorMsg ->
                  val itemId = viewModel.itemBermasalah.value
                  if (errorMsg != null && viewModel.checkoutResponse.value == null) {
                    val message = if (!itemId.isNullOrEmpty()) {
                      "Gagal melanjutkan checkout:\n$errorMsg\n(Kode produk: $itemId)"
                    } else {
                      "Gagal melanjutkan checkout:\n$errorMsg"
                    }
                    AlertDialog.Builder(this)
                      .setTitle("Checkout Gagal")
                      .setMessage(message)
                      .setPositiveButton("OK") { dialog, _ -> dialog.dismiss(); finish() }
                      .show()
                  }
                }

            viewModel.placeOrderResponse.observe(this) { response ->
                  Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                  if (intent.getStringExtra("SOURCE") == SOURCE_KERANJANG) {
                    getSharedPreferences("cart", MODE_PRIVATE).edit().remove("keranjang_items").apply()
                  }

                  val intent = Intent(this, HomeLoggedInActivity::class.java)
                  intent.putExtra("FRAGMENT_TARGET", "history")
                  startActivity(intent)
                  finish()
                }
          }

      private fun getItemsFromKeranjang(): List<RequestCheckoutItem> {
            val json = getSharedPreferences("cart", MODE_PRIVATE)
              .getString("keranjang_items", null)

            return if (!json.isNullOrEmpty()) {
                  Gson().fromJson(json, object : TypeToken<List<RequestCheckoutItem>>() {}.type)
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

            adapter.updateList(data.items)
            Log.d("CheckOutActivity", "adapter count: ${adapter.itemCount}")
            Log.d("CheckOutActivity", "produk_dibeli: ${Gson().toJson(data.items)}")
          }

      private fun updateDeliveryUI() {
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
          }

      private fun showDialog(title: String, message: String) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
          }
}
