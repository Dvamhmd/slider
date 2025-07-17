package com.example.dots.activityLoginTrue

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.dots.R
import com.example.dots.fragmentsBottomNav.HomeFragment
import com.example.dots.fragmentsLoggedIn.LoggedInCartFragment
import com.example.dots.fragmentsLoggedIn.LoggedInFavoriteFragment
import com.example.dots.fragmentsLoggedIn.LoggedInHistoryFragment
import com.example.dots.fragmentsLoggedIn.LoggedInHomeFragment
import com.example.dots.fragmentsLoggedIn.LoggedInProfilFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class HomeLoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_logged_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        val target = intent.getStringExtra("FRAGMENT_TARGET")





        //Setting Icon Bottom Navigation
        val bottomNavigation = findViewById<CurvedBottomNavigation>(R.id.bottomNavigation )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(id = 1, title = "Keranjang", R.drawable.ic_cart)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(id = 2, title = "Favorit", R.drawable.ic_favorite)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(id = 3, title = "Home", R.drawable.ic_home)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(id = 4, title = "Pesanan", R.drawable.ic_history)
        )

        bottomNavigation.add(
            CurvedBottomNavigation.Model(id = 5, title = "Profil", R.drawable.ic_profile)
        )



        //setting id fragment navigation
        bottomNavigation.setOnClickMenuListener {
            when(it.id) {
                1 -> { replaceFragment(LoggedInCartFragment()) }
                2 -> { replaceFragment(LoggedInFavoriteFragment()) }
                3 -> { replaceFragment(LoggedInHomeFragment()) }
                4 -> { replaceFragment(LoggedInHistoryFragment()) }
                5 -> { replaceFragment(LoggedInProfilFragment()) }
            }
        }



        //Button Default saat awal masuk
        replaceFragment(LoggedInHomeFragment())
        bottomNavigation.show(id = 3)

        if (target == "home") {
            // ganti fragment ke HomeFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoggedInHomeFragment())
                .commit()
        }


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }

}