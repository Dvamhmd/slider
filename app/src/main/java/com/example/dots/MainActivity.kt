package com.example.dots

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.dots.adapter.SliderAdapter
import com.example.dots.databinding.ActivityMainBinding
import com.example.dots.fragmentsSlider.Fragment_slider1
import com.example.dots.fragmentsSlider.Fragment_slider2
import com.example.dots.fragmentsSlider.Fragment_slider3

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentPage = 0
    private lateinit var adapter: SliderAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        TokenManager.initialize(this)



        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager

        adapter = SliderAdapter(supportFragmentManager)
        adapter.addFragment(Fragment_slider1(), title = "1st")
        adapter.addFragment(Fragment_slider2(), title = "2nd")
        adapter.addFragment(Fragment_slider3(), title = "3rd")

        viewPager.adapter = adapter
        dotsIndicator.setViewPager(viewPager)


        //Tombol Skip
        binding.skipText.setOnClickListener {

        }


        //Tombol Next
        binding.nextText.setOnClickListener {
            if (currentPage < adapter.count -1) {
                binding.viewPager.currentItem = currentPage + 1
            }
        }

        // Tombol Prev
        binding.prevText.setOnClickListener {
            if (currentPage > 0) {
                binding.viewPager.currentItem = currentPage - 1
            }
        }




        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPage = position
                updateButtons(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        updateButtons(0)


        findViewById<TextView>(R.id.startText).setOnClickListener {
            val intent = Intent(this, GetStartedActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.skipText).setOnClickListener {
            val intent = Intent(this, GetStartedActivity::class.java)
            startActivity(intent)
        }



    }

    //fungsi untuk bottom indicator
    private fun updateButtons(position: Int) {
        when (position) {
            0 -> { // Slide pertama
                binding.prevText.visibility = android.view.View.GONE
                binding.nextText.visibility = android.view.View.VISIBLE
                binding.startText.visibility = android.view.View.GONE
            }
            adapter.count - 1 -> { // Slide terakhir
                binding.prevText.visibility = android.view.View.VISIBLE
                binding.nextText.visibility = android.view.View.GONE
                binding.startText.visibility = android.view.View.VISIBLE
            }
            else -> { // Slide tengah
                binding.prevText.visibility = android.view.View.VISIBLE
                binding.nextText.visibility = android.view.View.VISIBLE
                binding.startText.visibility = android.view.View.GONE
            }
        }




    }
}