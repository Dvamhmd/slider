@file:Suppress("DEPRECATION")

package com.example.dots.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dots.fragmentsBestPromo.BestSellerFragment
import com.example.dots.fragmentsBestPromo.PromoFragment

class PromoBestAdapter (
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity)  {

    private val fragments = listOf(
        PromoFragment(),
        BestSellerFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}