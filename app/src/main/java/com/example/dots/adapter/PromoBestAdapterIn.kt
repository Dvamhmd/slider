package com.example.dots.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dots.fragmentsBestPromo.BestSellerFragmentIn
import com.example.dots.fragmentsBestPromo.PromoFragmentIn

class PromoBestAdapterIn (
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity)  {

    private val fragments = listOf(
        PromoFragmentIn(),
        BestSellerFragmentIn()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}