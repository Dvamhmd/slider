package com.example.dots

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dots.activityLoginTrue.HomeLoggedInActivity
import com.example.dots.exception.RegistrationException
import com.example.dots.models.RegisterRequest
import com.example.dots.viewmodel.RegisterViewModel
import com.example.dots.viewmodel.factory.RegisterViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val viewModel = ViewModelProvider(
            this,
            RegisterViewModelFactory(applicationContext)
        )[RegisterViewModel::class.java]

        val usernameLayout = findViewById<TextInputLayout>(R.id.usernameLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val phoneLayout = findViewById<TextInputLayout>(R.id.phoneLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val confirmPasswordLayout = findViewById<TextInputLayout>(R.id.confirmPasswordLayout)

        val usernameInput = findViewById<TextInputEditText>(R.id.username)
        val emailInput = findViewById<TextInputEditText>(R.id.email)
        val phoneInput = findViewById<TextInputEditText>(R.id.phone_number)
        val passwordInput = findViewById<TextInputEditText>(R.id.password)
        val confirmPasswordInput = findViewById<TextInputEditText>(R.id.confirm_password)

        val buttonRegister = findViewById<Button>(R.id.login)
        val loadingOverlay = findViewById<FrameLayout>(R.id.loadingOverlay)

        findViewById<View>(R.id.go_to_login).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        viewModel.registerResult.observe(this) { result ->
            loadingOverlay.visibility = View.GONE
            buttonRegister.isEnabled = true

            result.onSuccess {
                Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeLoggedInActivity::class.java))
                finish()
            }.onFailure { e ->
                Log.d("log create account", "$e")
                when (e) {

                    is RegistrationException -> {
                        e.fieldErrors["username"]?.let { usernameLayout.error = it.toString() }
                        e.fieldErrors["email"]?.let { emailLayout.error = it.toString() }
                        e.fieldErrors["no_hp"]?.let { phoneLayout.error = it.toString() }
                        e.fieldErrors["password"]?.let { passwordLayout.error = it.toString() }
                    }

                    else -> {
                        Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

        buttonRegister.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            val data = RegisterRequest(email,username,password, phone)

            usernameLayout.error = null
            emailLayout.error = null
            phoneLayout.error = null
            passwordLayout.error = null
            confirmPasswordLayout.error = null

            when {
                username.isEmpty() -> usernameLayout.error = "Username wajib diisi"
                email.isEmpty() -> emailLayout.error = "Email wajib diisi"
                phone.isEmpty() -> phoneLayout.error = "Nomor HP wajib diisi"
                password.length < 8 -> passwordLayout.error = "Password minimal 8 karakter"
                confirmPassword != password -> confirmPasswordLayout.error = "Konfirmasi tidak cocok"
                else -> {
                    buttonRegister.isEnabled = false
                    loadingOverlay.visibility = View.VISIBLE
                    viewModel.register(data)
                }
            }
        }
    }
}
