package com.example.dots.activityLoginTrue

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

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_out)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)

        back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }


        //mencari layout
        val voucherLayout1 = findViewById<LinearLayout>(R.id.voucher1)
        val voucherLayout2 = findViewById<LinearLayout>(R.id.voucher2)
        val voucherLayout3 = findViewById<LinearLayout>(R.id.voucher3)

        val voucherImage1 = voucherLayout1.findViewById<ImageView>(R.id.voucherImage)
        val voucherImage2 = voucherLayout2.findViewById<ImageView>(R.id.voucherImage)
        val voucherImage3 = voucherLayout3.findViewById<ImageView>(R.id.voucherImage)

        val voucherName1 = voucherLayout1.findViewById<TextView>(R.id.voucherName)
        val voucherName2 = voucherLayout2.findViewById<TextView>(R.id.voucherName)
        val voucherName3 = voucherLayout3.findViewById<TextView>(R.id.voucherName)

        val voucherStatus1 = voucherLayout1.findViewById<ImageView>(R.id.checkStatus)
        val voucherStatus2 = voucherLayout2.findViewById<ImageView>(R.id.checkStatus)
        val voucherStatus3 = voucherLayout3.findViewById<ImageView>(R.id.checkStatus)

        var isVoucher1Checked = false
        var isVoucher2Checked = false
        var isVoucher3Checked = false

        voucherImage1.setImageResource(R.drawable.promo_2)
        voucherImage2.setImageResource(R.drawable.promo_3)
        voucherImage3.setImageResource(R.drawable.promo_4)

        voucherName1.setText(R.string.promo_5_5)
        voucherName2.setText(R.string.promo_pancasila)
        voucherName3.setText(R.string.promo_jum_at)


        voucherLayout1.setOnClickListener{
            isVoucher1Checked = !isVoucher1Checked

            if (isVoucher1Checked){
                voucherStatus1.setImageResource(R.drawable.checked)
                voucherStatus2.setImageResource(R.drawable.uncheck)
                voucherStatus3.setImageResource(R.drawable.uncheck)
                Toast.makeText(this, "Voucher 5.5 dipilih", Toast.LENGTH_SHORT).show()
            }else{
                voucherStatus1.setImageResource(R.drawable.uncheck)
            }
        }

        voucherLayout2.setOnClickListener{
            isVoucher2Checked = !isVoucher2Checked

            if (isVoucher2Checked){
                voucherStatus1.setImageResource(R.drawable.uncheck)
                voucherStatus2.setImageResource(R.drawable.checked)
                voucherStatus3.setImageResource(R.drawable.uncheck)
                Toast.makeText(this, "Voucher Pancasila dipilih", Toast.LENGTH_SHORT).show()
            }else{
                voucherStatus2.setImageResource(R.drawable.uncheck)
            }
        }

        voucherLayout3.setOnClickListener{
            isVoucher3Checked = !isVoucher3Checked

            if (isVoucher3Checked){
                voucherStatus1.setImageResource(R.drawable.uncheck)
                voucherStatus2.setImageResource(R.drawable.uncheck)
                voucherStatus3.setImageResource(R.drawable.checked)
                Toast.makeText(this, "Voucher Pancasila dipilih", Toast.LENGTH_SHORT).show()
            }else{
                voucherStatus3.setImageResource(R.drawable.uncheck)
            }
        }












    }
}