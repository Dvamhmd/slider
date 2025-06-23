package com.example.dots

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.dots.activityLoginTrue.HomeLoggedInActivity
import com.example.dots.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

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

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val signUp = findViewById<TextView>(R.id.signUp)
        val goToHome = findViewById<Button>(R.id.login)

        // Tombol daftar
        signUp.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Observasi hasil login
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Login berhasil. Selamat datang ${it.data?.user?.nama}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeLoggedInActivity::class.java)
                startActivity(intent)
                finish()
            }.onFailure {
                Toast.makeText(this, "Login gagal: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Tombol login
        goToHome.setOnClickListener {
            val login = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(login, password)
            } else {
                Toast.makeText(this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
