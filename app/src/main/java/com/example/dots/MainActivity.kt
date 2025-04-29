package com.example.dots

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dots.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Fragment_slider1(), title = "1st")
        adapter.addFragment(Fragment_slider2(), title = "2nd")
        adapter.addFragment(Fragment_slider3(), title = "3rd")

        viewPager.adapter = adapter

        dotsIndicator.setViewPager(viewPager)






    }
}