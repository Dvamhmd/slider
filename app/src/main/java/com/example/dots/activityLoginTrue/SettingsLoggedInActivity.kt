package com.example.dots.activityLoginTrue

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.HomeActivityLogOut
import com.example.dots.R

class SettingsLoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_logged_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val back = findViewById<ImageView>(R.id.back)
        val about = findViewById<CardView>(R.id.about)
        val logOut = findViewById<CardView>(R.id.log_out)


        back.setOnClickListener {
            val intent = Intent(this, HomeLoggedInActivity::class.java)
            startActivity(intent)
        }

        about.setOnClickListener{
            val intent = Intent(this, AboutLoggedInActivity::class.java)
            startActivity(intent)
        }

        logOut.setOnClickListener{
            Toast.makeText(this, "Log Out Berhasil", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivityLogOut::class.java)
            startActivity(intent)
            finish()
        }



    }
}