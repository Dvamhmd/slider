package com.example.dots.fragmentsOrderType


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.dots.R
import com.example.dots.TokenManager


class FragmentPickUp : Fragment() {

    private lateinit var checkImages: List<ImageView>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pick_up, container, false)
        val saveAddress = view.findViewById<Button>(R.id.save_address)

        //mencari nearest store
        val nearestStore1 = view.findViewById<CardView>(R.id.nearest_store1)
        val nearestStore2 = view.findViewById<CardView>(R.id.nearest_store2)
        val nearestStore3 = view.findViewById<CardView>(R.id.nearest_store3)


        //mencari check
        val check1 = nearestStore1.findViewById<ImageView>(R.id.check)
        val check2 = nearestStore2.findViewById<ImageView>(R.id.check)
        val check3 = nearestStore3.findViewById<ImageView>(R.id.check)

        //mencari nama toko
        val store1 = nearestStore1.findViewById<TextView>(R.id.storeName)
        val store2 = nearestStore2.findViewById<TextView>(R.id.storeName)
        val store3 = nearestStore3.findViewById<TextView>(R.id.storeName)

        //mencari alamat toko
        val storeAddress1 = nearestStore1.findViewById<TextView>(R.id.storeAddress)
        val storeAddress2 = nearestStore2.findViewById<TextView>(R.id.storeAddress)
        val storeAddress3 = nearestStore3.findViewById<TextView>(R.id.storeAddress)

        //mencari jarak toko
        val storeDistance1 = nearestStore1.findViewById<TextView>(R.id.storeDistance)
        val storeDistance2 = nearestStore2.findViewById<TextView>(R.id.storeDistance)
        val storeDistance3 = nearestStore3.findViewById<TextView>(R.id.storeDistance)



        store1.text = getString(R.string.teh_idaman_concat)
        store2.text = getString(R.string.teh_idaman_gejayan)
        store3.text = getString(R.string.teh_idaman_wonosari)

        storeAddress1.text = getString(R.string.concat_address)
        storeAddress2.text = getString(R.string.gejayan_address)
        storeAddress3.text = getString(R.string.wonosari_address)

        storeDistance1.text = getString(R.string._1_5_km)
        storeDistance2.text = getString(R.string._2_km)
        storeDistance3.text = getString(R.string._12_km)





        //list check
        checkImages = listOf(check1, check2, check3)



        check1.setOnClickListener { selectStore(0) }
        check2.setOnClickListener { selectStore(1) }
        check3.setOnClickListener { selectStore(2) }




        //navigasi ke home
        saveAddress.setOnClickListener {
            TokenManager.saveDeliveryOption("pickup")
            requireActivity().setResult(AppCompatActivity.RESULT_OK)
            requireActivity().finish()
        }





        restoreSelectedStore()




        return view
    }


    private fun selectStore(selectedIndex: Int) {
        // Update centang semua gambar
        checkImages.forEachIndexed { index, image ->
            image.setImageResource(if (index == selectedIndex) R.drawable.checked else R.drawable.uncheck)
        }

        // Simpan data toko
        val storeName = when (selectedIndex) {
            0 -> "Concat"
            1 -> "Gejayan"
            2 -> "Wonosari"
            else -> "Unknown Store"
        }

        val idToko = when (selectedIndex) {
            0 -> "T001"
            1 -> "T002"
            2 -> "T003"
            else -> ""
        }

        TokenManager.saveSelectedStore(idToko)

        Toast.makeText(requireContext(), "Teh Idaman $storeName", Toast.LENGTH_SHORT).show()
    }

    private fun restoreSelectedStore() {
        val selectedStore = when (TokenManager.getSelectedStore()) {
            "T001" -> 0
            "T002" -> 1
            "T003" -> 2
            else -> -1
        }

        if (selectedStore != -1) {
            selectStore(selectedStore)
        }
    }



}