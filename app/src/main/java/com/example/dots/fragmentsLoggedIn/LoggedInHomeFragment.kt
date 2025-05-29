package com.example.dots.fragmentsLoggedIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.dots.R
import com.example.dots.activityLoginTrue.ChatLoggedInActivity
import com.example.dots.activityLoginTrue.SettingsLoggedInActivity
import com.example.dots.adapter.PromoBestAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LoggedInHomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_logged_in_home, container, false)

        val settings = view.findViewById<ImageView>(R.id.settings)
        val chat = view.findViewById<ImageView>(R.id.chat)


        // navigasi settings
        settings.setOnClickListener{
            val intent = Intent(requireContext(), SettingsLoggedInActivity::class.java)
            startActivity(intent)
        }

        // navigasi chat
        chat.setOnClickListener{
            val intent = Intent(requireContext(), ChatLoggedInActivity::class.java)
            startActivity(intent)
        }

        // setting gambar dan judul tea series
        val teaSeriesLayout = view.findViewById<View>(R.id.tea_series)
        val teaSeriesImage = teaSeriesLayout.findViewById<ImageView>(R.id.image_series)
        val teaSeriesName = teaSeriesLayout.findViewById<TextView>(R.id.name_series)
        teaSeriesImage.setImageResource(R.drawable.tea_series)
        teaSeriesName.setText(R.string.tea_series)

        // squash series
        val squashSeriesLayout = view.findViewById<View>(R.id.squash_series)
        val squashSeriesImage = squashSeriesLayout.findViewById<ImageView>(R.id.image_series)
        val squashSeriesName = squashSeriesLayout.findViewById<TextView>(R.id.name_series)
        squashSeriesImage.setImageResource(R.drawable.squash_series)
        squashSeriesName.text = getString(R.string.squash_series)

        // milky series
        val milkySeriesLayout = view.findViewById<View>(R.id.milky_series)
        val milkySeriesImage = milkySeriesLayout.findViewById<ImageView>(R.id.image_series)
        val milkySeriesName = milkySeriesLayout.findViewById<TextView>(R.id.name_series)
        milkySeriesImage.setImageResource(R.drawable.milky_series)
        milkySeriesName.text = getString(R.string.milky_series)

        // thai series
        val thaiSeriesLayout = view.findViewById<View>(R.id.thai_series)
        val thaiSeriesImage = thaiSeriesLayout.findViewById<ImageView>(R.id.image_series)
        val thaiSeriesName = thaiSeriesLayout.findViewById<TextView>(R.id.name_series)
        thaiSeriesImage.setImageResource(R.drawable.thai_series)
        thaiSeriesName.text = getString(R.string.thai_series)

        // yakult series
        val yakultSeriesLayout = view.findViewById<View>(R.id.yakult_series)
        val yakultSeriesImage = yakultSeriesLayout.findViewById<ImageView>(R.id.image_series)
        val yakultSeriesName = yakultSeriesLayout.findViewById<TextView>(R.id.name_series)
        yakultSeriesImage.setImageResource(R.drawable.yakult_series)
        yakultSeriesName.text = getString(R.string.yakult_series)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        val adapter = PromoBestAdapter(requireActivity())
        viewPager.adapter = adapter

        //Link TabLayout dan ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Promo"
                1 -> tab.text = "Best Seller"
            }
        }.attach()




        return view
    }


}