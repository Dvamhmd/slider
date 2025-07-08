package com.example.dots.activityLoginTrue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R
import java.text.NumberFormat
import java.util.Locale

class DetailProductInActivity : AppCompatActivity() {

    private lateinit var btnPlus: ImageView
    private lateinit var btnMinus: ImageView
    private lateinit var tvQuantity: TextView


    private var quantity = 1



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

        val back = findViewById<ImageView>(R.id.back)

        var isFavorite = false
        val favorite = findViewById<ImageView>(R.id.favorite)

        var isAddedToCart = false
        val cartLayout = findViewById<FrameLayout>(R.id.add_to_cart)

        val cart = cartLayout.findViewById<ImageView>(R.id.cart)

        val checkOut = findViewById<FrameLayout>(R.id.goToCheckOut)




        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))



        //menerima variabel
//        val getProductId = intent.getStringExtra("ID_PRODUK")
        val getProductImage = intent.getIntExtra("GAMBAR_PRODUK", 0)
        val getProductPrice = intent.getIntExtra("HARGA_PRODUK", 0)
        val getProductName = intent.getIntExtra("NAMA_PRODUK", 0)
        val getProductDesc = intent.getIntExtra("DESKRIPSI_PRODUK", 0)



        //mencari layout
        val productImage = findViewById<ImageView>(R.id.product_image)
        val productPrice = findViewById<TextView>(R.id.product_price)
        val productName = findViewById<TextView>(R.id.product_name)
        val productDesc = findViewById<TextView>(R.id.product_description)



        //setting
        if (getProductImage != 0){
            productImage.setImageResource(getProductImage)
        }

        if (getProductPrice != 0){
            val formatted = "Rp ${formatter.format(getProductPrice)}"
            productPrice.text = formatted
        }

        if (getProductName != 0){
            productName.setText(getProductName)
        }

        if (getProductDesc != 0){
            productDesc.setText(getProductDesc)
        }


        back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


        favorite.setOnClickListener{
            isFavorite = !isFavorite

            if (isFavorite){
                favorite.setImageResource(R.drawable.fav)
                Toast.makeText(this, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show()

            } else {
                favorite.setImageResource(R.drawable.no_fav)
                Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()

            }

        }

        cart.setOnClickListener{
            isAddedToCart = !isAddedToCart

            if (isAddedToCart){
                cart.setImageResource(R.drawable.ic_addedtocart)
                Toast.makeText(this, "Ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()

            } else {
                cart.setImageResource(R.drawable.ic_addtocart)
                Toast.makeText(this, "Dihapus dari keranjang", Toast.LENGTH_SHORT).show()

            }


        }

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

        checkOut.setOnClickListener{
            val intent = Intent(this, CheckOutActivity::class.java)
            startActivity(intent)
        }

    }


    @SuppressLint("SetTextI18n")
    private fun updateQuantity(){
        tvQuantity.text = quantity.toString()
    }

}