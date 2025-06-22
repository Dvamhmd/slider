package com.example.dots.detailPromo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

class PromoActivityOut1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_promo_out1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)
        val backToMenu = findViewById<Button>(R.id.back_to_menu)

        back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
            finish()
        }

        backToMenu.setOnClickListener{
            val intent = Intent(this, HomeActivityLogOut::class.java)
            startActivity(intent)
            finish()
        }







        //menerima intent
        val getPromoImage = intent.getIntExtra("GAMBAR_PROMO", 0)
        val getPromoDiscount = intent.getIntExtra("DISKON_PROMO", 0)
        val getPromoTitle = intent.getIntExtra("JUDUL_PROMO", 0)
        val getPromoDate = intent.getIntExtra("EXPIRED_DATE", 0)
        val getPromoDesc = intent.getIntExtra("DESKRIPSI_1", 0)
        val getTos1 = intent.getIntExtra("TOS_1", 0)
        val getTos2 = intent.getIntExtra("TOS_2", 0)
        val getTos3 = intent.getIntExtra("TOS_3", 0)

        val getItemPromo1 = intent.getIntExtra("ITEM_PROMO1", 0)
        val getItemPromo2 = intent.getIntExtra("ITEM_PROMO2", 0)
        val getItemPromo3 = intent.getIntExtra("ITEM_PROMO3", 0)


        val getItemName1 = intent.getIntExtra("NAMA_ITEM1", 0)
        val getItemName2 = intent.getIntExtra("NAMA_ITEM2", 0)
        val getItemName3 = intent.getIntExtra("NAMA_ITEM3", 0)




        //mencari layout
        val promoImage = findViewById<ImageView>(R.id.promo_image)
        val promoDiscount = findViewById<TextView>(R.id.promo_discount)
        val promoTitle = findViewById<TextView>(R.id.promo_title)
        val promoDate = findViewById<TextView>(R.id.promo_date)
        val promoDesc = findViewById<TextView>(R.id.promo_description)
        val tos1 =findViewById<TextView>(R.id.term_of_use_1)
        val tos2 =findViewById<TextView>(R.id.term_of_use_2)
        val tos3 =findViewById<TextView>(R.id.term_of_use_3)

        val itemPromo1 = findViewById<LinearLayout>(R.id.product_promo1)
        val itemPromo2 = findViewById<LinearLayout>(R.id.product_promo2)
        val itemPromo3 = findViewById<LinearLayout>(R.id.product_promo3)

        val itemImage1 = itemPromo1.findViewById<ImageView>(R.id.productImage)
        val itemImage2 = itemPromo2.findViewById<ImageView>(R.id.productImage)
        val itemImage3 = itemPromo3.findViewById<ImageView>(R.id.productImage)

        val itemName1 = itemPromo1.findViewById<TextView>(R.id.productName)
        val itemName2 = itemPromo2.findViewById<TextView>(R.id.productName)
        val itemName3 = itemPromo3.findViewById<TextView>(R.id.productName)




        if (getPromoImage != 0){
            promoImage.setImageResource(getPromoImage)
        }
        if (getPromoDiscount != 0){
            promoDiscount.setText(getPromoDiscount)
        }
        if (getPromoTitle != 0){
            promoTitle.setText(getPromoTitle)
        }
        if (getPromoDate != 0){
            promoDate.setText(getPromoDate)
        }
        if (getPromoDesc != 0){
            promoDesc.setText(getPromoDesc)
        }
        if (getTos1 != 0){
            tos1.setText(getTos1)
        }
        if (getTos2 != 0){
            tos2.setText(getTos2)
        }
        if (getTos3 != 0){
            tos3.setText(getTos3)
        }

        if (getItemPromo1 != 0){
            itemImage1.setImageResource(getItemPromo1)
        }
        if (getItemPromo2 != 0){
            itemImage2.setImageResource(getItemPromo2)
        }
        if (getItemPromo3 != 0){
            itemImage3.setImageResource(getItemPromo3)
        }

        if (getItemName1 != 0){
            itemName1.setText(getItemName1)
        }
        if (getItemName2 != 0){
            itemName2.setText(getItemName2)
        }
        if (getItemName3 != 0){
            itemName3.setText(getItemName3)
        }
    }
}