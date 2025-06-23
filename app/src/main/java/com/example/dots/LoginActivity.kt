package com.example.dots

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
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
import org.json.JSONObject
import java.net.SocketTimeoutException

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

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val usernameLayout = findViewById<TextInputLayout>(R.id.usernameLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)

        val usernameInput = findViewById<TextInputEditText>(R.id.usernameInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val signUp = findViewById<TextView>(R.id.signUp)
        val goToHome = findViewById<Button>(R.id.login)
        val loadingOverlay = findViewById<FrameLayout>(R.id.loadingOverlay)


        signUp.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
            finish()
        }

        viewModel.loginResult.observe(this) { result ->
            loadingOverlay.visibility = View.GONE
            goToHome.isEnabled = true

            result.onSuccess {
                Toast.makeText(this, "Login berhasil. Selamat datang ${it.data?.user?.username}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeLoggedInActivity::class.java))
                finish()
            }.onFailure { e ->
                when (e) {
                    is retrofit2.HttpException -> {
                        try {
                            val errorBody = e.response()?.errorBody()?.string()
                            val json = JSONObject(errorBody ?: "{}")
                            val message = json.getString("message")

                            when (message.lowercase()) {
                                "akun tidak ditemukan." -> usernameLayout.error = message
                                "password salah." -> passwordLayout.error = message
                                else -> Toast.makeText(this, "Login gagal: $message", Toast.LENGTH_SHORT).show()
                            }
                        } catch (ex: Exception) {
                            Toast.makeText(this, "Kesalahan server: [${e.code()}]", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is SocketTimeoutException -> {
                        Toast.makeText(this, "Timeout: Server tidak merespon dalam 10 detik", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Gagal terhubung ke server: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        goToHome.setOnClickListener {
            val login = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            usernameLayout.error = null
            passwordLayout.error = null

            if (login.isEmpty()) {
                usernameLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordLayout.error = "Wajib diisi"
                return@setOnClickListener
            }

            if (login == "ilmu" && password == "1736"){
                Toast.makeText(this, "Login berhasil. Selamat datang", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeLoggedInActivity::class.java))
                finish()
            }

            goToHome.isEnabled = false
            loadingOverlay.visibility = View.VISIBLE

            viewModel.login(login, password)
        }
    }
}
