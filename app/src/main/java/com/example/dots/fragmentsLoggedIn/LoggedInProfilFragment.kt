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
import com.example.dots.viewmodel.UserViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dots.repository.UserRepository
import com.example.dots.viewmodel.factory.UserViewModelFactory
import com.bumptech.glide.Glide



class LoggedInProfilFragment : Fragment() {

    private lateinit var editProfileLauncher: ActivityResultLauncher<Intent>
    private lateinit var usernameDisplay: TextView
    private lateinit var nameDisplay: TextView
    private lateinit var emailDisplay: TextView
    private lateinit var phoneDisplay: TextView
    private var photoUriStr: String? = null
    private lateinit var userViewModel: UserViewModel





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logged_in_profil, container, false)
        val editProfile = view.findViewById<ImageView>(R.id.edit)

        nameDisplay = view.findViewById(R.id.name_display)
        usernameDisplay = view.findViewById(R.id.username_display)
        emailDisplay = view.findViewById(R.id.email_display)
        phoneDisplay = view.findViewById(R.id.phone_display)

        // Inisialisasi ViewModel
        val factory = UserViewModelFactory(requireContext())
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

// Observasi data user
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            nameDisplay.text = user?.nama ?: "Tidak ada nama"
              usernameDisplay.text = user?.username ?: "Tidak ada username"
              emailDisplay.text = user?.email ?: "Tidak ada email"
              phoneDisplay.text = user?.noHp ?: "Tidak ada nomor"
            photoUriStr = user?.foto

              val photoProfile = view.findViewById<ImageView>(R.id.photo_profile)
              user?.foto?.let {
                Glide.with(this)
                  .load(it)
                  .placeholder(R.drawable.profile) // gambar default jika loading
                  .into(photoProfile)
              }
        }

// Ambil data user dari API
        userViewModel.fetchUser()






        editProfileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            userViewModel.fetchUser()
        }




        editProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)

            intent.putExtra("NAME", nameDisplay.text.toString())
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