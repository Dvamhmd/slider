package com.example.dots.activityLoginTrue

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.R

class EditProfileActivity : AppCompatActivity() {

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var selectedPhotoUri: Uri? = null



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

        val save = findViewById<Button>(R.id.save)

        val usernameInput = findViewById<EditText>(R.id.edit_username)
        val emailInput = findViewById<EditText>(R.id.edit_email)
        val phoneInput = findViewById<EditText>(R.id.edit_phone_number)
        val photoProfile = findViewById<ImageView>(R.id.photo_profile)
        val photoUriStr = intent.getStringExtra("PHOTO_URI")





        pickImageLauncher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                selectedPhotoUri = uri

                try {
                    contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }

                photoProfile.setImageURI(uri)
            }
        }

        photoProfile.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        if (photoUriStr != null) {
            val uri = Uri.parse(photoUriStr)
            photoProfile.setImageURI(uri)
            selectedPhotoUri = uri
        }





        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")





        usernameInput.setText(username)
        emailInput.setText(email)
        phoneInput.setText(phone)



        save.setOnClickListener{
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val phone = phoneInput.text.toString()

            val resultIntent = Intent().apply {
                putExtra("USERNAME", username)
                putExtra("EMAIL", email)
                putExtra("PHONE", phone)
                selectedPhotoUri?.let { putExtra("PHOTO_URI", it.toString()) }
            }



            setResult(RESULT_OK, resultIntent)
            Toast.makeText(this, "Simpan berhasil!", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}