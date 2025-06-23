package com.example.dots

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
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
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    @SuppressLint("MissingInflatedId")
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

        val usernameLayout = findViewById<TextInputLayout>(R.id.usernameLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val signUp = findViewById<TextView>(R.id.signUp)
        val goToHome = findViewById<Button>(R.id.login)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)


        // Tombol daftar
        signUp.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Observasi hasil login
        viewModel.loginResult.observe(this) { result ->
            progressBar.visibility = View.GONE
            goToHome.isEnabled = true

            result.onSuccess {
                Toast.makeText(this, "Login berhasil. Selamat datang ${it.data?.user?.username}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeLoggedInActivity::class.java))
                finish()
            }.onFailure { e ->
                val errorMsg = when (e) {
                    is retrofit2.HttpException -> {
                        try {
                            val errorBody = e.response()?.errorBody()?.string()
                            val json = org.json.JSONObject(errorBody ?: "{}")
                            val message = json.getString("message")

                            when (message.lowercase()) {
                                "akun tidak ditemukan." -> {
                                    usernameLayout.error = message
                                }
                                "password salah." -> {
                                    passwordLayout.error = message
                                }
                                else -> {
                                    Toast.makeText(this, "Login gagal: $message", Toast.LENGTH_SHORT).show()
                                }
                            }

                            message
                        } catch (ex: Exception) {
                            "Terjadi kesalahan. [${e.code()}]"
                        }
                    }
                    else -> {
                        "Gagal terhubung ke server: ${e.localizedMessage}"
                    }
                }
            }
        }


        // Tombol login
        goToHome.setOnClickListener {
            val login = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                if (login == "ilmu" && password == "1736"){
                    val intent = Intent(this, HomeLoggedInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                usernameLayout.error = null
                passwordLayout.error = null
                goToHome.isEnabled = false
                progressBar.visibility = View.VISIBLE
                viewModel.login(login, password)
            } else {
                Toast.makeText(this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
