package com.example.dots.categoryOut

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
import com.example.dots.HomeActivityLogOut
import com.example.dots.R
import java.text.NumberFormat
import java.util.Locale

class YakultSeriesOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_yakult_series_out)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener{
            val intent = Intent(this, HomeActivityLogOut::class.java)
            startActivity(intent)
            finish()
        }

        //Mencari Baris Tea Series
        val series1 = findViewById<LinearLayout>(R.id.series1)
        val series2 = findViewById<LinearLayout>(R.id.series2)
        val series3 = findViewById<LinearLayout>(R.id.series3)



        //Mencari Series Image Kiri
        val leftImage1 = series1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = series2.findViewById<ImageView>(R.id.productImage_left)
        val leftImage3 = series3.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Series Image Kanan
        val rightImage1 = series1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = series2.findViewById<ImageView>(R.id.productImage_right)
        val rightImage3 = series3.findViewById<ImageView>(R.id.productImage_right)




        //Mencari Series Title Kiri
        val leftTitle1 = series1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = series2.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle3 = series3.findViewById<TextView>(R.id.productTitle_left)

        //Mencari Series Title Kanan
        val rightTitle1 = series1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = series2.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle3 = series3.findViewById<TextView>(R.id.productTitle_right)




        //Mencari Tea Series Price Kiri
        val priceLeft1 = series1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = series2.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft3 = series3.findViewById<TextView>(R.id.productPrice_left)

        //Mencari Tea Series Price Kanan
        val priceRight1 = series1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = series2.findViewById<TextView>(R.id.productPrice_right)
        val priceRight3 = series3.findViewById<TextView>(R.id.productPrice_right)


        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceYakultSeries = 9000

        //Setting Tea Series Image Kiri
        leftImage1.setImageResource(R.drawable.ys_strawberry)
        leftImage2.setImageResource(R.drawable.ys_mango)
        leftImage3.setImageResource(R.drawable.ys_orange)

        //Setting Tea Series Image Kanan
        rightImage1.setImageResource(R.drawable.ys_grape)
        rightImage2.setImageResource(R.drawable.ys_lychee)
        rightImage3.setImageResource(R.drawable.ys_melon)






        //Setting Series Title Kiri
        leftTitle1.text = getString(R.string.yakult_strawberry)
        leftTitle2.text = getString(R.string.yakult_mango)
        leftTitle3.text = getString(R.string.yakult_orange)

        //Setting Series Title Kanan
        rightTitle1.text = getString(R.string.yakult_grape)
        rightTitle2.text = getString(R.string.yakult_lychee)
        rightTitle3.text = getString(R.string.yakult_melon)





        //Setting Series Price kiri
        priceLeft1.text = formatter.format(priceYakultSeries)
        priceLeft2.text = formatter.format(priceYakultSeries)
        priceLeft3.text = formatter.format(priceYakultSeries)

        //Setting Series Price kanan
        priceRight1.text = formatter.format(priceYakultSeries)
        priceRight2.text = formatter.format(priceYakultSeries)
        priceRight3.text = formatter.format(priceYakultSeries)





    }
}