package com.example.dots.activityLoginTrue

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dots.R
import com.example.dots.viewmodel.UserViewModel
import com.example.dots.viewmodel.factory.UserViewModelFactory

class EditProfileActivity : AppCompatActivity() {

      private lateinit var pickImageLauncher: ActivityResultLauncher<String>
      private var selectedPhotoUri: Uri? = null
      private lateinit var userViewModel: UserViewModel

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_edit_profile)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                  val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                  v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                  insets
                }

            val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
            windowInsetsController.isAppearanceLightStatusBars = true

            // ViewModel
            userViewModel = ViewModelProvider(this, UserViewModelFactory(applicationContext))[UserViewModel::class.java]

            val save = findViewById<Button>(R.id.save)
          val nameInput = findViewById<EditText>(R.id.edit_name)
            val usernameInput = findViewById<EditText>(R.id.edit_username)
            val emailInput = findViewById<EditText>(R.id.edit_email)
            val phoneInput = findViewById<EditText>(R.id.edit_phone_number)
            val photoProfile = findViewById<ImageView>(R.id.photo_profile)
          val loadingOverlay = findViewById<FrameLayout>(R.id.loadingOverlay)
            val photoUriStr = intent.getStringExtra("PHOTO_URI")

            pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                  uri?.let {
                    selectedPhotoUri = it
                    try {
                      contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    } catch (e: SecurityException) {
                      e.printStackTrace()
                    }
                    photoProfile.setImageURI(it)
                  }
                }

            photoProfile.setOnClickListener {
                  pickImageLauncher.launch("image/*")
                }

          photoUriStr.let {
              Glide.with(this)
                  .load(it)
                  .placeholder(R.drawable.profile) // gambar default jika loading
                  .into(photoProfile)
          }


          nameInput.setText(intent.getStringExtra("NAME"))
            usernameInput.setText(intent.getStringExtra("USERNAME"))
            emailInput.setText(intent.getStringExtra("EMAIL"))
            phoneInput.setText(intent.getStringExtra("PHONE"))

          save.setOnClickListener {
              val name = nameInput.text.toString()
              val username = usernameInput.text.toString()
              val email = emailInput.text.toString()
              val phone = phoneInput.text.toString()
              loadingOverlay.visibility = View.VISIBLE

              val compressedImageData = selectedPhotoUri?.let { compressImage(it) }
              userViewModel.updateProfile(name, username, email, phone, compressedImageData)

              userViewModel.updateResult.observe(this) { result ->
                  loadingOverlay.visibility = View.GONE
                  if (result.isSuccess) {
                      Toast.makeText(this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                      finish()
                  } else {
                      Toast.makeText(this, "Gagal: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                  }
              }
          }

      }

    private fun compressImage(uri: Uri): ByteArray? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)
            val outputStream = java.io.ByteArrayOutputStream()
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 70, outputStream)
            outputStream.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
