package com.example.dots.fragmentsLoggedIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.dots.R
import com.example.dots.activityLoginTrue.EditProfileActivity


class LoggedInProfilFragment : Fragment() {

    private lateinit var editProfileLauncher: ActivityResultLauncher<Intent>
    private lateinit var usernameDisplay: TextView
    private lateinit var emailDisplay: TextView
    private lateinit var phoneDisplay: TextView
    private var photoUriStr: String? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logged_in_profil, container, false)
        val editProfile = view.findViewById<ImageView>(R.id.edit)

        usernameDisplay = view.findViewById(R.id.username_display)
        emailDisplay = view.findViewById(R.id.email_display)
        phoneDisplay = view.findViewById(R.id.phone_display)





        editProfileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val data = result.data
                val username = data?.getStringExtra("USERNAME")
                val email = data?.getStringExtra("EMAIL")
                val phone = data?.getStringExtra("PHONE")
                photoUriStr = data?.getStringExtra("PHOTO_URI")



                usernameDisplay.text = username ?: "Tidak ada username"
                emailDisplay.text = email ?: "Tidak ada email"
                phoneDisplay.text = phone ?: "Tidak ada nomor"

                val photoProfile = view?.findViewById<ImageView>(R.id.photo_profile)
                if (photoUriStr != null) {
                    val photoUri = android.net.Uri.parse(photoUriStr)
                    photoProfile?.setImageURI(photoUri)
                }

            }
        }




        editProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)

            intent.putExtra("USERNAME", usernameDisplay.text.toString())
            intent.putExtra("EMAIL", emailDisplay.text.toString())
            intent.putExtra("PHONE", phoneDisplay.text.toString())

            photoUriStr?.let {
                intent.putExtra("PHOTO_URI", it)
            }

            editProfileLauncher.launch(intent)
        }














        return view
    }


}