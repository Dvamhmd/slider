package com.example.dots

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dots.adapter.FragmentAdapter
import com.example.dots.fragmentsBottomNav.CartFragment
import com.example.dots.fragmentsBottomNav.FavoriteFragment
import com.example.dots.fragmentsBottomNav.HistoryFragment
import com.example.dots.fragmentsBottomNav.HomeFragment
import com.example.dots.fragmentsBottomNav.ProfilFragment
import com.google.android.material.tabs.TabLayout
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class HomeActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





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

        //setting id bottom navigation
        bottomNavigation.setOnClickMenuListener {
            when(it.id) {
                1 -> { replaceFragment(CartFragment()) }
                2 -> { replaceFragment(FavoriteFragment()) }
                3 -> { replaceFragment(HomeFragment()) }
                4 -> { replaceFragment(HistoryFragment()) }
                5 -> { replaceFragment(ProfilFragment()) }
            }
        }




        //Button Default saat awal masuk
        replaceFragment(HomeFragment())
        bottomNavigation.show(id = 3)


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }



}