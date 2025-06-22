package com.example.dots.category

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
import java.text.NumberFormat
import java.util.Locale

class SeeAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_see_all)
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

        //Mencari Baris Squash Series
        val series6 = findViewById<LinearLayout>(R.id.series6)
        val series7 = findViewById<LinearLayout>(R.id.series7)

        //Mencari Baris Milky Series
        val series8 = findViewById<LinearLayout>(R.id.series8)
        val series9 = findViewById<LinearLayout>(R.id.series9)
        val series10 = findViewById<LinearLayout>(R.id.series10)
        val series11 = findViewById<LinearLayout>(R.id.series11)
        val series12 = findViewById<LinearLayout>(R.id.series12)

        //Mencari Baris Thai Series
        val series13 = findViewById<LinearLayout>(R.id.series13)
        val series14 = findViewById<LinearLayout>(R.id.series14)

        //Mencari Baris Yakult Series
        val series15 = findViewById<LinearLayout>(R.id.series15)
        val series16 = findViewById<LinearLayout>(R.id.series16)
        val series17 = findViewById<LinearLayout>(R.id.series17)


        //Mencari Tea Series Image Kiri
        val leftImage1 = series1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = series2.findViewById<ImageView>(R.id.productImage_left)
        val leftImage3 = series3.findViewById<ImageView>(R.id.productImage_left)
        val leftImage4 = series4.findViewById<ImageView>(R.id.productImage_left)
        val leftImage5 = series5.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Tea Series Image Kanan
        val rightImage1 = series1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = series2.findViewById<ImageView>(R.id.productImage_right)
        val rightImage3 = series3.findViewById<ImageView>(R.id.productImage_right)
        val rightImage4 = series4.findViewById<ImageView>(R.id.productImage_right)
        val rightImage5 = series5.findViewById<ImageView>(R.id.productImage_right)



        //Mencari Squash Series Image Kiri
        val leftImage6 = series6.findViewById<ImageView>(R.id.productImage_left)
        val leftImage7 = series7.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Squash Series Image Kanan
        val rightImage6 = series6.findViewById<ImageView>(R.id.productImage_right)
        val rightImage7 = series7.findViewById<ImageView>(R.id.productImage_right)



        //Mencari Milky Series Image Kiri
        val leftImage8 = series8.findViewById<ImageView>(R.id.productImage_left)
        val leftImage9 = series9.findViewById<ImageView>(R.id.productImage_left)
        val leftImage10 = series10.findViewById<ImageView>(R.id.productImage_left)
        val leftImage11 = series11.findViewById<ImageView>(R.id.productImage_left)
        val leftImage12 = series12.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Milky Series Image Kanan
        val rightImage8 = series8.findViewById<ImageView>(R.id.productImage_right)
        val rightImage9 = series9.findViewById<ImageView>(R.id.productImage_right)
        val rightImage10 = series10.findViewById<ImageView>(R.id.productImage_right)
        val rightImage11 = series11.findViewById<ImageView>(R.id.productImage_right)
        val rightImage12 = series12.findViewById<ImageView>(R.id.productImage_right)



        //Mencari Thai Series Image Kiri
        val leftImage13 = series13.findViewById<ImageView>(R.id.productImage_left)
        val leftImage14 = series14.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Thai Series Image Kanan
        val rightImage13 = series13.findViewById<ImageView>(R.id.productImage_right)
        val rightImage14 = series14.findViewById<ImageView>(R.id.productImage_right)




        //Mencari Yakult Series Image Kiri
        val leftImage15 = series15.findViewById<ImageView>(R.id.productImage_left)
        val leftImage16 = series16.findViewById<ImageView>(R.id.productImage_left)
        val leftImage17 = series17.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Yakult Series Image Kanan
        val rightImage15 = series15.findViewById<ImageView>(R.id.productImage_right)
        val rightImage16 = series16.findViewById<ImageView>(R.id.productImage_right)
        val rightImage17 = series17.findViewById<ImageView>(R.id.productImage_right)










        //Mencari Series title Kiri
        val leftTitle1 = series1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = series2.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle3 = series3.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle4 = series4.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle5 = series5.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle6 = series6.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle7 = series7.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle8 = series8.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle9 = series9.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle10 = series10.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle11 = series11.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle12 = series12.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle13 = series13.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle14 = series14.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle15 = series15.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle16 = series16.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle17 = series17.findViewById<TextView>(R.id.productTitle_left)


        val rightTitle1 = series1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = series2.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle3 = series3.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle4 = series4.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle5 = series5.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle6 = series6.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle7 = series7.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle8 = series8.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle9 = series9.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle10 = series10.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle11 = series11.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle12 = series12.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle13 = series13.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle14 = series14.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle15 = series15.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle16 = series16.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle17 = series17.findViewById<TextView>(R.id.productTitle_right)



        //Mencari Series Price
        val priceLeft1 = series1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = series2.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft3 = series3.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft4 = series4.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft5 = series5.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft6 = series6.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft7 = series7.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft8 = series8.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft9 = series9.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft10 = series10.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft11 = series11.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft12 = series12.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft13 = series13.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft14 = series14.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft15 = series15.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft16 = series16.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft17 = series17.findViewById<TextView>(R.id.productPrice_left)

        val priceRight1 = series1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = series2.findViewById<TextView>(R.id.productPrice_right)
        val priceRight3 = series3.findViewById<TextView>(R.id.productPrice_right)
        val priceRight4 = series4.findViewById<TextView>(R.id.productPrice_right)
        val priceRight5 = series5.findViewById<TextView>(R.id.productPrice_right)
        val priceRight6 = series6.findViewById<TextView>(R.id.productPrice_right)
        val priceRight7 = series7.findViewById<TextView>(R.id.productPrice_right)
        val priceRight8 = series8.findViewById<TextView>(R.id.productPrice_right)
        val priceRight9 = series9.findViewById<TextView>(R.id.productPrice_right)
        val priceRight10 = series10.findViewById<TextView>(R.id.productPrice_right)
        val priceRight11 = series11.findViewById<TextView>(R.id.productPrice_right)
        val priceRight12 = series12.findViewById<TextView>(R.id.productPrice_right)
        val priceRight13 = series13.findViewById<TextView>(R.id.productPrice_right)
        val priceRight14 = series14.findViewById<TextView>(R.id.productPrice_right)
        val priceRight15 = series15.findViewById<TextView>(R.id.productPrice_right)
        val priceRight16 = series16.findViewById<TextView>(R.id.productPrice_right)
        val priceRight17 = series17.findViewById<TextView>(R.id.productPrice_right)








        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceMilkySeries = 10000
        val priceSquashSeries = 8000
        val priceJasmineTea = 2500
        val priceFlavouredTea = 5000
        val priceTarikTea = 7000
        val priceThaiTeaOri = 6000
        val priceThaiTeaMachiato = 8000
        val priceThaiTeaOreo = 9000
        val priceThaiTeaChoco = 9000
        val priceYakultSeries = 9000

        leftImage1.setImageResource(R.drawable.ts_jasmine)
        leftImage2.setImageResource(R.drawable.ts_teatarik)
        leftImage3.setImageResource(R.drawable.ts_orange)
        leftImage4.setImageResource(R.drawable.ts_grape)
        leftImage5.setImageResource(R.drawable.ts_strawberry)

        leftImage6.setImageResource(R.drawable.ss_strawberry)
        leftImage7.setImageResource(R.drawable.ss_orange)

        leftImage8.setImageResource(R.drawable.ms_chocooreo)
        leftImage9.setImageResource(R.drawable.ms_caramel)
        leftImage10.setImageResource(R.drawable.ms_bubblegum)
        leftImage11.setImageResource(R.drawable.ms_avocado)
        leftImage12.setImageResource(R.drawable.ms_redvelvet)

        leftImage13.setImageResource(R.drawable.tt_original)
        leftImage14.setImageResource(R.drawable.tt_oreo)

        leftImage15.setImageResource(R.drawable.ys_strawberry)
        leftImage16.setImageResource(R.drawable.ys_mango)
        leftImage17.setImageResource(R.drawable.ys_orange)






        rightImage1.setImageResource(R.drawable.ts_markisa)
        rightImage2.setImageResource(R.drawable.ts_lychee)
        rightImage3.setImageResource(R.drawable.ts_mango)
        rightImage4.setImageResource(R.drawable.ts_melon)
        rightImage5.setImageResource(R.drawable.ts_apple)

        rightImage6.setImageResource(R.drawable.ss_lychee)
        rightImage7.setImageResource(R.drawable.ss_grape)

        rightImage8.setImageResource(R.drawable.ms_royalchoco)
        rightImage9.setImageResource(R.drawable.ms_silverqueen)
        rightImage10.setImageResource(R.drawable.ms_blackcurrant)
        rightImage11.setImageResource(R.drawable.ms_matcha)
        rightImage12.setImageResource(R.drawable.ms_taro)

        rightImage13.setImageResource(R.drawable.tt_macchiato)
        rightImage14.setImageResource(R.drawable.tt_choco)

        rightImage15.setImageResource(R.drawable.ys_grape)
        rightImage16.setImageResource(R.drawable.ys_lychee)
        rightImage17.setImageResource(R.drawable.ys_melon)






        leftTitle1.text = getString(R.string.jasmine_tea)
        leftTitle2.text = getString(R.string.tea_tarik)
        leftTitle3.text = getString(R.string.lemon_tea)
        leftTitle4.text = getString(R.string.grape_tea)
        leftTitle5.text = getString(R.string.strawberry_tea)

        leftTitle6.text = getString(R.string.strawberry_squash)
        leftTitle7.text = getString(R.string.orange_squash)

        leftTitle8.text = getString(R.string.choco_oreo_creamy)
        leftTitle9.text = getString(R.string.caramel_creamy)
        leftTitle10.text = getString(R.string.bubble_gum_creamy)
        leftTitle11.text = getString(R.string.avocado_creamy)
        leftTitle12.text = getString(R.string.red_velvet_creamy)

        leftTitle13.text = getString(R.string.thai_tea_original)
        leftTitle14.text = getString(R.string.thai_tea_oreo)

        leftTitle15.text = getString(R.string.yakult_strawberry)
        leftTitle16.text = getString(R.string.yakult_mango)
        leftTitle17.text = getString(R.string.yakult_orange)








        rightTitle1.text = getString(R.string.markisa_tea)
        rightTitle2.text = getString(R.string.lychee_tea)
        rightTitle3.text = getString(R.string.mango_tea)
        rightTitle4.text = getString(R.string.melon_tea)
        rightTitle5.text = getString(R.string.apple_tea)

        rightTitle6.text = getString(R.string.lychee_squash)
        rightTitle7.text = getString(R.string.grape_squash)

        rightTitle8.text = getString(R.string.royal_choco_creamy)
        rightTitle9.text = getString(R.string.silverqueen_creamy)
        rightTitle10.text = getString(R.string.blackcurrant_creamy)
        rightTitle11.text = getString(R.string.matcha_creamy)
        rightTitle12.text = getString(R.string.taro_creamy)

        rightTitle13.text = getString(R.string.thai_tea_macchiato)
        rightTitle14.text = getString(R.string.thai_tea_choco)

        rightTitle15.text = getString(R.string.yakult_grape)
        rightTitle16.text = getString(R.string.yakult_lychee)
        rightTitle17.text = getString(R.string.yakult_melon)





        //Setting Best Seller Price kiri
        priceLeft1.text = formatter.format(priceJasmineTea)
        priceLeft2.text = formatter.format(priceTarikTea)
        priceLeft3.text = formatter.format(priceFlavouredTea)
        priceLeft4.text = formatter.format(priceFlavouredTea)
        priceLeft5.text = formatter.format(priceFlavouredTea)

        priceLeft6.text = formatter.format(priceSquashSeries)
        priceLeft7.text = formatter.format(priceSquashSeries)

        priceLeft8.text = formatter.format(priceMilkySeries)
        priceLeft9.text = formatter.format(priceMilkySeries)
        priceLeft10.text = formatter.format(priceMilkySeries)
        priceLeft11.text = formatter.format(priceMilkySeries)
        priceLeft12.text = formatter.format(priceMilkySeries)

        priceLeft13.text = formatter.format(priceThaiTeaOri)
        priceLeft14.text = formatter.format(priceThaiTeaOreo)

        priceLeft15.text = formatter.format(priceYakultSeries)
        priceLeft16.text = formatter.format(priceYakultSeries)
        priceLeft17.text = formatter.format(priceYakultSeries)




        //Setting Best Seller Price kanan
        priceRight1.text = formatter.format(priceFlavouredTea)
        priceRight2.text = formatter.format(priceFlavouredTea)
        priceRight3.text = formatter.format(priceFlavouredTea)
        priceRight4.text = formatter.format(priceFlavouredTea)
        priceRight5.text = formatter.format(priceFlavouredTea)

        priceRight6.text = formatter.format(priceSquashSeries)
        priceRight7.text = formatter.format(priceSquashSeries)

        priceRight8.text = formatter.format(priceMilkySeries)
        priceRight9.text = formatter.format(priceMilkySeries)
        priceRight10.text = formatter.format(priceMilkySeries)
        priceRight11.text = formatter.format(priceMilkySeries)
        priceRight12.text = formatter.format(priceMilkySeries)

        priceRight13.text = formatter.format(priceThaiTeaMachiato)
        priceRight14.text = formatter.format(priceThaiTeaChoco)

        priceRight15.text = formatter.format(priceYakultSeries)
        priceRight16.text = formatter.format(priceYakultSeries)
        priceRight17.text = formatter.format(priceYakultSeries)



    }
}