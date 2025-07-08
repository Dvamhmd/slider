package com.example.dots.fragmentsLoggedIn

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.dots.R
import com.example.dots.activityLoginTrue.ChatLoggedInActivity
import com.example.dots.activityLoginTrue.OrderTypeActivity
import com.example.dots.activityLoginTrue.SettingsLoggedInActivity
import com.example.dots.adapter.KategoriAdapter
import com.example.dots.adapter.PromoBestAdapterIn
import com.example.dots.repository.KategoriRepository
import com.example.dots.viewmodel.KategoriViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoggedInHomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var kategoriViewModel: KategoriViewModel
    private lateinit var kategoriRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_logged_in_home, container, false)

        val settings = view.findViewById<ImageView>(R.id.settings)
        val chat = view.findViewById<ImageView>(R.id.chat)
        val deliveryLayout = view.findViewById<LinearLayout>(R.id.delivery_layout)
        val seeAllProducts = view.findViewById<TextView>(R.id.see_products)

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        kategoriRecycler = view.findViewById(R.id.kategoriRecyclerView)

        // Navigasi Settings dan Chat
        settings.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsLoggedInActivity::class.java))
        }
        chat.setOnClickListener {
            startActivity(Intent(requireContext(), ChatLoggedInActivity::class.java))
        }

        // Navigasi OrderType
        deliveryLayout.setOnClickListener {
            startActivity(Intent(requireContext(), OrderTypeActivity::class.java))
        }

        // Navigasi See All
        seeAllProducts.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.dots.category.SeeAll::class.java))
        }

        // Adapter Promo & Best Seller
        val adapter = PromoBestAdapterIn(requireActivity())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Promo"
                1 -> "Best Seller"
                else -> ""
            }
        }.attach()

        // Kategori API (RecyclerView horizontal)
        kategoriRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        kategoriViewModel = ViewModelProvider(this)[KategoriViewModel::class.java]

        kategoriViewModel.kategoriList.observe(viewLifecycleOwner) { kategoriList ->
            kategoriList?.let {
                val adapterKategori = KategoriAdapter(it) { kategori ->
                    val nama = kategori.namaKategori?.lowercase()

                    val intent = when {
                        nama?.contains("tea") == true && !nama.contains("thai") -> Intent(requireContext(), com.example.dots.category.TeaSeries::class.java)
                        nama?.contains("squash") == true -> Intent(requireContext(), com.example.dots.category.SquashSeries::class.java)
                        nama?.contains("milky") == true -> Intent(requireContext(), com.example.dots.category.MilkySeries::class.java)
                        nama?.contains("thai") == true -> Intent(requireContext(), com.example.dots.category.ThaiSeries::class.java)
                        nama?.contains("yakult") == true -> Intent(requireContext(), com.example.dots.category.YakultSeries::class.java)
                        else -> null
                    }

                    startActivity(intent)

//                    if (intent != null) {
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(requireContext(), "Kategori belum tersedia", Toast.LENGTH_SHORT).show()
//                    }
                }
                kategoriRecycler.adapter = adapterKategori
            }
        }

        kategoriViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }

        kategoriViewModel.fetchKategori()

        return view
    }
}
