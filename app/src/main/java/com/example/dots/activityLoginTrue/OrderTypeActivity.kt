package com.example.dots.activityLoginTrue


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.dots.R
import com.example.dots.TokenManager
import com.example.dots.fragmentsOrderType.FragmentDelivery
import com.example.dots.fragmentsOrderType.FragmentPickUp

class OrderTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContentView(R.layout.activity_order_type)

          val deliveryOption = TokenManager.getDeliveryOption() // "delivery" atau "pickup"

          val back = findViewById<ImageView>(R.id.back)
          val slider = findViewById<View>(R.id.slider)
          val btnDelivery = findViewById<TextView>(R.id.btnDelivery)
          val btnPickup = findViewById<TextView>(R.id.btnPickup)
          val toggleGroup = findViewById<LinearLayout>(R.id.toggleGroup)

          ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
              }

          toggleGroup.post {
                slider.layoutParams.width = toggleGroup.width / 2
                slider.requestLayout()

                if (deliveryOption == "pickup") {
                  val targetX = toggleGroup.width / 2f
                  slider.x = targetX
                  btnPickup.setTextColor(Color.WHITE)
                  btnDelivery.setTextColor(ContextCompat.getColor(this, R.color.green))
                  showFragment(FragmentPickUp())
                } else {
                  slider.x = 0f
                  btnDelivery.setTextColor(Color.WHITE)
                  btnPickup.setTextColor(ContextCompat.getColor(this, R.color.green))
                  showFragment(FragmentDelivery())
                }
              }

          btnDelivery.setOnClickListener {
                slider.animate().x(0f).setDuration(200).start()
                btnDelivery.setTextColor(Color.WHITE)
                btnPickup.setTextColor(ContextCompat.getColor(this, R.color.green))
                TokenManager.saveDeliveryOption("delivery")
                showFragment(FragmentDelivery())
              }

          btnPickup.setOnClickListener {
                val targetX = toggleGroup.width / 2f
                slider.animate().x(targetX).setDuration(200).start()
                btnPickup.setTextColor(Color.WHITE)
                btnDelivery.setTextColor(ContextCompat.getColor(this, R.color.green))
                TokenManager.saveDeliveryOption("pickup")
                showFragment(FragmentPickUp())
              }

          back.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
                finish()
              }
    }


    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}