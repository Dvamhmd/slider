package com.example.dots.activityLoginTrue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dots.R
import com.example.dots.network.ApiClient
import com.example.dots.repository.KeranjangRepository
import com.example.dots.utilities.formatRupiah
import com.example.dots.viewmodel.KeranjangViewModel
import java.text.NumberFormat
import java.util.*

class DetailProductInActivity : AppCompatActivity() {

    private lateinit var btnPlus: ImageView
    private lateinit var btnMinus: ImageView
    private lateinit var tvQuantity: TextView
    private lateinit var keranjangViewModel: KeranjangViewModel

    private var quantity = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_product_in)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        // --- Ambil data dari Intent ---
        val idProduk = intent.getStringExtra("ID_PRODUK") ?: ""
        Log.d("Detail produk", "Id produk: $idProduk")
        val idToko = intent.getStringExtra("ID_TOKO") ?: "T001"
        val getProductImage = intent.getIntExtra("GAMBAR_PRODUK", 0)
        val getProductPrice = intent.getIntExtra("HARGA_PRODUK", 0)
        val getProductName = intent.getIntExtra("NAMA_PRODUK", 0)
        val getProductDesc = intent.getIntExtra("DESKRIPSI_PRODUK", 0)

        // --- Inisialisasi ViewModel ---
        val repository = KeranjangRepository(ApiClient.getApiService(this))
        keranjangViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return KeranjangViewModel(repository) as T
            }
        })[KeranjangViewModel::class.java]

        // --- Observe Error (opsional) ---
        keranjangViewModel.error.observe(this) {
            it?.let {
                Toast.makeText(this, "Gagal tambah ke keranjang: $it", Toast.LENGTH_SHORT).show()
            }
        }

        // --- Inisialisasi Komponen ---
        val back = findViewById<ImageView>(R.id.back)
        val favorite = findViewById<ImageView>(R.id.favorite)
        val cartLayout = findViewById<FrameLayout>(R.id.add_to_cart)
        val cart = cartLayout.findViewById<ImageView>(R.id.cart)
        val checkOut = findViewById<FrameLayout>(R.id.goToCheckOut)

        val productImage = findViewById<ImageView>(R.id.product_image)
        val productPrice = findViewById<TextView>(R.id.product_price)
        val productName = findViewById<TextView>(R.id.product_name)
        val productDesc = findViewById<TextView>(R.id.product_description)

        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        // --- Tampilkan data produk ---
        if (getProductImage != 0) productImage.setImageResource(getProductImage)
        if (getProductPrice != 0) productPrice.text = "Rp ${formatter.format(getProductPrice)}"
        if (getProductName != 0) productName.setText(getProductName)
        if (getProductDesc != 0) productDesc.setText(getProductDesc)

        // --- Back Button ---
        back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        // --- Favorite Toggle ---
        var isFavorite = false
        favorite.setOnClickListener {
            isFavorite = !isFavorite
            favorite.setImageResource(if (isFavorite) R.drawable.fav else R.drawable.no_fav)
            Toast.makeText(this,
                if (isFavorite) "Ditambahkan ke favorit" else "Dihapus dari favorit",
                Toast.LENGTH_SHORT
            ).show()
        }

        // --- Quantity Control ---
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        tvQuantity = findViewById(R.id.tvQuantity)

        updateQuantity()

        btnPlus.setOnClickListener {
            quantity++
            updateQuantity()
        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantity()
            }
        }

        // --- Tombol Add to Cart ---
        cart.setOnClickListener {
            if (idProduk.isNotBlank() && idToko.isNotBlank()) {
                keranjangViewModel.tambahKeranjang(idProduk, idToko, quantity)
                cart.setImageResource(R.drawable.ic_addedtocart)
                Toast.makeText(this, "$quantity item ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                Log.d("DetailProduct", "Add to cart: $idProduk x$quantity")
            } else {
                Toast.makeText(this, "Produk tidak valid", Toast.LENGTH_SHORT).show()
            }
        }

        // --- Tombol Order Now / Checkout ---
        checkOut.setOnClickListener {
            val intent = Intent(this, CheckOutActivity::class.java)
            intent.putExtra("ID_PRODUK", idProduk)
            intent.putExtra("JUMLAH", quantity)
            intent.putExtra("SOURCE", "PRODUK")
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuantity() {
        tvQuantity.text = quantity.toString()
    }
}
