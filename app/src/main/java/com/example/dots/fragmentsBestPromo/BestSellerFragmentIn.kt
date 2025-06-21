package com.example.dots.fragmentsBestPromo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dots.R
import java.text.NumberFormat
import java.util.Locale


class BestSellerFragmentIn : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_best_seller_in, container, false)


        //Mencari Best Seller Layout
        val bestSellerLayout1 = view.findViewById<LinearLayout>(R.id.bestSeller_1)
        val bestSellerLayout2 = view.findViewById<LinearLayout>(R.id.bestSeller_2)
        val bestSellerLayout3 = view.findViewById<LinearLayout>(R.id.bestSeller_3)

        //Mencari Best Seller Image Kiri
        val leftImage1 = bestSellerLayout1.findViewById<ImageView>(R.id.productImage_left)
        val leftImage2 = bestSellerLayout2.findViewById<ImageView>(R.id.productImage_left)
        val leftImage3 = bestSellerLayout3.findViewById<ImageView>(R.id.productImage_left)

        //Mencari Best Seller Image Kanan
        val rightImage1 = bestSellerLayout1.findViewById<ImageView>(R.id.productImage_right)
        val rightImage2 = bestSellerLayout2.findViewById<ImageView>(R.id.productImage_right)
        val rightImage3 = bestSellerLayout3.findViewById<ImageView>(R.id.productImage_right)

        //Mencari Best Seller Title Kiri
        val leftTitle1 = bestSellerLayout1.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle2 = bestSellerLayout2.findViewById<TextView>(R.id.productTitle_left)
        val leftTitle3 = bestSellerLayout3.findViewById<TextView>(R.id.productTitle_left)

        //Mencari Best Seller Title Kanan
        val rightTitle1 = bestSellerLayout1.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle2 = bestSellerLayout2.findViewById<TextView>(R.id.productTitle_right)
        val rightTitle3 = bestSellerLayout3.findViewById<TextView>(R.id.productTitle_right)

        //Mencari Best Seller Price Kiri
        val priceLeft1 = bestSellerLayout1.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft2 = bestSellerLayout2.findViewById<TextView>(R.id.productPrice_left)
        val priceLeft3 = bestSellerLayout3.findViewById<TextView>(R.id.productPrice_left)

        //Mencari Best Seller Price Kanan
        val priceRight1 = bestSellerLayout1.findViewById<TextView>(R.id.productPrice_right)
        val priceRight2 = bestSellerLayout2.findViewById<TextView>(R.id.productPrice_right)
        val priceRight3 = bestSellerLayout3.findViewById<TextView>(R.id.productPrice_right)

        //Format Currency Rupiah
        val formatter = NumberFormat.getInstance(Locale("in", "ID"))

        //Harga Tiap Menu
        val priceJasmineTea = 2500
        val priceChocoOreo = 10000
        val priceThaiTeaOri = 6000
        val priceBerrySquash = 8000
        val priceMatchaCreamy = 10000
        val priceTaroCreamy = 10000









        //Setting Best Seller Image Kiri
        leftImage1.setImageResource(R.drawable.jasmine_tea)
        leftImage2.setImageResource(R.drawable.thai_tea)
        leftImage3.setImageResource(R.drawable.matcha)

        //Setting Best Seller Image Kanan
        rightImage1.setImageResource(R.drawable.choco_cream)
        rightImage2.setImageResource(R.drawable.strawberry_squash)
        rightImage3.setImageResource(R.drawable.taro)

        //Setting Best Seller Title Kiri
        leftTitle1.text = getString(R.string.jasmine_tea)
        leftTitle2.text = getString(R.string.thai_tea_ori)
        leftTitle3.text = getString(R.string.matcha_creamy)

        //Setting Best Seller Title Kanan
        rightTitle1.text = getString(R.string.choco_oreo)
        rightTitle2.text = getString(R.string.berry_squash)
        rightTitle3.text = getString(R.string.taro_creamy)

        //Setting Best Seller Price kiri
        priceLeft1.text = formatter.format(priceJasmineTea)
        priceLeft2.text = formatter.format(priceThaiTeaOri)
        priceLeft3.text = formatter.format(priceMatchaCreamy)

        //Setting Best Seller Price kanan
        priceRight1.text = formatter.format(priceChocoOreo)
        priceRight2.text = formatter.format(priceBerrySquash)
        priceRight3.text = formatter.format(priceTaroCreamy)







        // Inflate the layout for this fragment
        return view

    }


}