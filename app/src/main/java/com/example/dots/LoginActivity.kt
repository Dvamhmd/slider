package com.example.dots

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.activityLoginTrue.HomeLoggedInActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val usernameVerification = "ilmu"
        val passwordVerification = "1736"

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)



        val goToHome = findViewById<Button>(R.id.login)

        goToHome.setOnClickListener{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {

                if (username == usernameVerification && password == passwordVerification) {

                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeLoggedInActivity::class.java)
                    startActivity(intent)

                }else {

                    Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()

                }

            }else {

                Toast.makeText(this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show()

            }









        }





    }
}