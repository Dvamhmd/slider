package com.example.dots.category

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R
import com.example.dots.activityLoginTrue.DetailProductInActivity
import java.text.NumberFormat
import java.util.Locale

class MilkySeries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_milky_series)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        //Mencari Baris Tea Series
        val series1 = findViewById<LinearLayout>(R.id.series1)
        val series2 = findViewById<LinearLayout>(R.id.series2)
        val series3 = findViewById<LinearLayout>(R.id.series3)
        val series4 = findViewById<LinearLayout>(R.id.series4)
        val series5 = findViewById<LinearLayout>(R.id.series5)



        //Mencari Series Image Kiri
        val leftImage1 = series1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = series2.findViewById<ImageView>(R.id.productImage_left)
        val leftImage3 = series3.findViewById<ImageView>(R.id.productImage_left)
        val leftImage4 = series4.findViewById<ImageView>(R.id.productImage_left)
        val leftImage5 = series5.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Series Image Kanan
        val rightImage1 = series1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = series2.findViewById<ImageView>(R.id.productImage_right)
        val rightImage3 = series3.findViewById<ImageView>(R.id.productImage_right)
        val rightImage4 = series4.findViewById<ImageView>(R.id.productImage_right)
        val rightImage5 = series5.findViewById<ImageView>(R.id.productImage_right)




        //Mencari Tea Series Title Kiri
        val leftTitle1 = series1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = series2.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle3 = series3.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle4 = series4.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle5 = series5.findViewById<TextView>(R.id.productTitle_left)

        //Mencari Tea Series Title Kanan
        val rightTitle1 = series1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = series2.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle3 = series3.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle4 = series4.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle5 = series5.findViewById<TextView>(R.id.productTitle_right)




        //Mencari Tea Series Price Kiri
        val priceLeft1 = series1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = series2.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft3 = series3.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft4 = series4.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft5 = series5.findViewById<TextView>(R.id.productPrice_left)

        //Mencari Tea Series Price Kanan
        val priceRight1 = series1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = series2.findViewById<TextView>(R.id.productPrice_right)
        val priceRight3 = series3.findViewById<TextView>(R.id.productPrice_right)
        val priceRight4 = series4.findViewById<TextView>(R.id.productPrice_right)
        val priceRight5 = series5.findViewById<TextView>(R.id.productPrice_right)



        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceMilkySeries = 10000

        //Setting Tea Series Image Kiri
        leftImage1.setImageResource(R.drawable.ms_chocooreo)
        leftImage2.setImageResource(R.drawable.ms_caramel)
        leftImage3.setImageResource(R.drawable.ms_bubblegum)
        leftImage4.setImageResource(R.drawable.ms_avocado)
        leftImage5.setImageResource(R.drawable.ms_redvelvet)

        //Setting Tea Series Image Kanan
        rightImage1.setImageResource(R.drawable.ms_royalchoco)
        rightImage2.setImageResource(R.drawable.ms_silverqueen)
        rightImage3.setImageResource(R.drawable.ms_blackcurrant)
        rightImage4.setImageResource(R.drawable.ms_matcha)
        rightImage5.setImageResource(R.drawable.ms_taro)






        //Setting Best Seller Title Kiri
        leftTitle1.text = getString(R.string.choco_oreo_creamy)
        leftTitle2.text = getString(R.string.caramel_creamy)
        leftTitle3.text = getString(R.string.bubble_gum_creamy)
        leftTitle4.text = getString(R.string.avocado_creamy)
        leftTitle5.text = getString(R.string.red_velvet_creamy)

        //Setting Best Seller Title Kanan
        rightTitle1.text = getString(R.string.royal_choco_creamy)
        rightTitle2.text = getString(R.string.silverqueen_creamy)
        rightTitle3.text = getString(R.string.blackcurrant_creamy)
        rightTitle4.text = getString(R.string.matcha_creamy)
        rightTitle5.text = getString(R.string.taro_creamy)





        //Setting Best Seller Price kiri
        priceLeft1.text = formatter.format(priceMilkySeries)
        priceLeft2.text = formatter.format(priceMilkySeries)
        priceLeft3.text = formatter.format(priceMilkySeries)
        priceLeft4.text = formatter.format(priceMilkySeries)
        priceLeft5.text = formatter.format(priceMilkySeries)

        //Setting Best Seller Price kanan
        priceRight1.text = formatter.format(priceMilkySeries)
        priceRight2.text = formatter.format(priceMilkySeries)
        priceRight3.text = formatter.format(priceMilkySeries)
        priceRight4.text = formatter.format(priceMilkySeries)
        priceRight5.text = formatter.format(priceMilkySeries)

        leftImage1.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD011")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_chocooreo_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.choco_oreo_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_choco_oreo_desc)

            startActivity(intent)
        }

        leftImage2.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD013")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_caramel_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.caramel_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_choco_caramel_desc)

            startActivity(intent)
        }

        leftImage3.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD015")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_bgum_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.bubble_gum_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_bubble_gum_desc)

            startActivity(intent)
        }

        leftImage4.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD017")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_avocado_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.avocado_creamy)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_avocado_desc)

            startActivity(intent)
        }

        leftImage5.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD019")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_rv_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.red_velvet_creamy)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_red_velvet_desc)

            startActivity(intent)
        }








        rightImage1.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD012")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_royalchoco_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.royal_choco_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_royal_choco_desc)

            startActivity(intent)
        }

        rightImage2.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD014")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_silverqueen_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.silverqueen_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_silver_queen_desc)

            startActivity(intent)
        }

        rightImage3.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD016")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_blackcurrant_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.blackcurrant_creamy2)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_blackcurrant_desc)

            startActivity(intent)
        }

        rightImage4.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD018")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_matcha_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.matcha_creamy)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_matcha_desc)

            startActivity(intent)
        }

        rightImage5.setOnClickListener{
            val intent = Intent(this, DetailProductInActivity::class.java)

            intent.putExtra("ID_PRODUK", "PROD020")
            intent.putExtra("GAMBAR_PRODUK", R.drawable.ms_taro_core)
            intent.putExtra("HARGA_PRODUK", priceMilkySeries)
            intent.putExtra("NAMA_PRODUK", R.string.taro_creamy)
            intent.putExtra("DESKRIPSI_PRODUK", R.string.ms_taro_desc)

            startActivity(intent)
        }











    }
}