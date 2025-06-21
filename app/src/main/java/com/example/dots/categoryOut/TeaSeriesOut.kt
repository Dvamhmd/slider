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

class TeaSeriesOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tea_series_out)
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
        val teaSeries1 = findViewById<LinearLayout>(R.id.tea_series1)
        val teaSeries2 = findViewById<LinearLayout>(R.id.tea_series2)
        val teaSeries3 = findViewById<LinearLayout>(R.id.tea_series3)
        val teaSeries4 = findViewById<LinearLayout>(R.id.tea_series4)
        val teaSeries5 = findViewById<LinearLayout>(R.id.tea_series5)





        //Mencari Tea Series Image Kiri
        val leftImage1 = teaSeries1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = teaSeries2.findViewById<ImageView>(R.id.productImage_left)
        val leftImage3 = teaSeries3.findViewById<ImageView>(R.id.productImage_left)
        val leftImage4 = teaSeries4.findViewById<ImageView>(R.id.productImage_left)
        val leftImage5 = teaSeries5.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Tea Series Image Kanan
        val rightImage1 = teaSeries1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = teaSeries2.findViewById<ImageView>(R.id.productImage_right)
        val rightImage3 = teaSeries3.findViewById<ImageView>(R.id.productImage_right)
        val rightImage4 = teaSeries4.findViewById<ImageView>(R.id.productImage_right)
        val rightImage5 = teaSeries5.findViewById<ImageView>(R.id.productImage_right)




        //Mencari Tea Series Title Kiri
        val leftTitle1 = teaSeries1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = teaSeries2.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle3 = teaSeries3.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle4 = teaSeries4.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle5 = teaSeries5.findViewById<TextView>(R.id.productTitle_left)

        //Mencari Tea Series Title Kanan
        val rightTitle1 = teaSeries1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = teaSeries2.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle3 = teaSeries3.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle4 = teaSeries4.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle5 = teaSeries5.findViewById<TextView>(R.id.productTitle_right)





        //Mencari Tea Series Price Kiri
        val priceLeft1 = teaSeries1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = teaSeries2.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft3 = teaSeries3.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft4 = teaSeries4.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft5 = teaSeries5.findViewById<TextView>(R.id.productPrice_left)

        //Mencari Tea Series Price Kanan
        val priceRight1 = teaSeries1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = teaSeries2.findViewById<TextView>(R.id.productPrice_right)
        val priceRight3 = teaSeries3.findViewById<TextView>(R.id.productPrice_right)
        val priceRight4 = teaSeries4.findViewById<TextView>(R.id.productPrice_right)
        val priceRight5 = teaSeries5.findViewById<TextView>(R.id.productPrice_right)





        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceJasmineTea = 2500
        val priceFlavouredTea = 5000
        val priceTarikTea = 7000







        //Setting Tea Series Image Kiri
        leftImage1.setImageResource(R.drawable.ts_jasmine)
        leftImage2.setImageResource(R.drawable.ts_teatarik)
        leftImage3.setImageResource(R.drawable.ts_orange)
        leftImage4.setImageResource(R.drawable.ts_grape)
        leftImage5.setImageResource(R.drawable.ts_strawberry)

        //Setting Tea Series Image Kanan
        rightImage1.setImageResource(R.drawable.ts_markisa)
        rightImage2.setImageResource(R.drawable.ts_lychee)
        rightImage3.setImageResource(R.drawable.ts_mango)
        rightImage4.setImageResource(R.drawable.ts_melon)
        rightImage5.setImageResource(R.drawable.ts_apple)






        //Setting Best Seller Title Kiri
        leftTitle1.text = getString(R.string.jasmine_tea)
        leftTitle2.text = getString(R.string.tea_tarik)
        leftTitle3.text = getString(R.string.lemon_tea)
        leftTitle4.text = getString(R.string.grape_tea)
        leftTitle5.text = getString(R.string.strawberry_tea)

        //Setting Best Seller Title Kanan
        rightTitle1.text = getString(R.string.markisa_tea)
        rightTitle2.text = getString(R.string.lychee_tea)
        rightTitle3.text = getString(R.string.mango_tea)
        rightTitle4.text = getString(R.string.melon_tea)
        rightTitle5.text = getString(R.string.apple_tea)





        //Setting Best Seller Price kiri
        priceLeft1.text = formatter.format(priceJasmineTea)
        priceLeft2.text = formatter.format(priceTarikTea)
        priceLeft3.text = formatter.format(priceFlavouredTea)
        priceLeft4.text = formatter.format(priceFlavouredTea)
        priceLeft5.text = formatter.format(priceFlavouredTea)

        //Setting Best Seller Price kanan
        priceRight1.text = formatter.format(priceFlavouredTea)
        priceRight2.text = formatter.format(priceFlavouredTea)
        priceRight3.text = formatter.format(priceFlavouredTea)
        priceRight4.text = formatter.format(priceFlavouredTea)
        priceRight5.text = formatter.format(priceFlavouredTea)

    }
}