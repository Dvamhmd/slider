package com.example.dots.categoryOut

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
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

class SquashSeriesOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_squash_series_out)
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

        //Mencari Baris Squash Series
        val series1 = findViewById<LinearLayout>(R.id.series1)
        val series2 = findViewById<LinearLayout>(R.id.series2)

        //Mencari Series Image Kiri
        val leftImage1 = series1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = series2.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Series Image Kanan
        val rightImage1 = series1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = series2.findViewById<ImageView>(R.id.productImage_right)



        //Mencari Tea Series Title Kiri
        val leftTitle1 = series1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = series2.findViewById<TextView>(R.id.productTitle_left)

        //Mencari Tea Series Title Kanan
        val rightTitle1 = series1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = series2.findViewById<TextView>(R.id.productTitle_right)




        //Mencari Tea Series Price Kiri
        val priceLeft1 = series1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = series2.findViewById<TextView>(R.id.productPrice_left)

        //Mencari Tea Series Price Kanan
        val priceRight1 = series1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = series2.findViewById<TextView>(R.id.productPrice_right)



        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceSquashSeries = 8000


        //Setting Tea Series Image Kiri
        leftImage1.setImageResource(R.drawable.ss_strawberry)
        leftImage2.setImageResource(R.drawable.ss_orange)

        //Setting Tea Series Image Kanan
        rightImage1.setImageResource(R.drawable.ss_lychee)
        rightImage2.setImageResource(R.drawable.ss_grape)






        //Setting Best Seller Title Kiri
        leftTitle1.text = getString(R.string.strawberry_squash)
        leftTitle2.text = getString(R.string.orange_squash)

        //Setting Best Seller Title Kanan
        rightTitle1.text = getString(R.string.lychee_squash)
        rightTitle2.text = getString(R.string.grape_squash)





        //Setting Best Seller Price kiri
        priceLeft1.text = formatter.format(priceSquashSeries)
        priceLeft2.text = formatter.format(priceSquashSeries)

        //Setting Best Seller Price kanan
        priceRight1.text = formatter.format(priceSquashSeries)
        priceRight2.text = formatter.format(priceSquashSeries)


        series1.setOnClickListener{
            Toast.makeText(this, "Login dulu yuk! sebelum lanjut", Toast.LENGTH_SHORT).show()
        }

        series2.setOnClickListener{
            Toast.makeText(this, "Login dulu yuk! sebelum lanjut", Toast.LENGTH_SHORT).show()
        }









    }
}