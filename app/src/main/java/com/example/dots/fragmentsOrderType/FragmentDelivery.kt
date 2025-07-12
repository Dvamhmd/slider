package com.example.dots.fragmentsOrderType

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.dots.R
import com.example.dots.activityLoginTrue.CreateAddressActivity



class FragmentDelivery : Fragment() {

    private lateinit var checkImages: List<ImageView>
    private lateinit var addressEmptyCard: View
    private val addressList = mutableListOf<Map<String, String>>()
    private lateinit var addressContainer: ViewGroup


    private val addressResultLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val data = result.data
            val newAddress = mapOf(
                "address" to (data?.getStringExtra("ADDRESS") ?: "-"),
                "label" to (data?.getStringExtra("ADDRESS_LABEL") ?: "-"),
                "detail" to (data?.getStringExtra("ADDRESS_DETAIL") ?: "-"),
                "name" to (data?.getStringExtra("ADDRESS_NAME") ?: "-"),
                "phone" to (data?.getStringExtra("ADDRESS_PHONE") ?: "-")
            )
            addressList.add(newAddress)
            renderAllAddresses()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_delivery, container, false)

        //alamat kosong sebelum tambah alamat
        addressEmptyCard = view.findViewById(R.id.address_empty_card)
        addressContainer = view.findViewById(R.id.address_container)


        //variabel alamat
        val address = arguments?.getString("ADDRESS")
        val addressDetail = arguments?.getString("ADDRESS_DETAIL")
        val addressLabel = arguments?.getString("ADDRESS_LABEL")
        val addressName =  arguments?.getString("ADDRESS_NAME")
        val addressPhone = arguments?.getString("ADDRESS_PHONE")




        if (!addressDetail.isNullOrEmpty()) {
            val firstAddress = mapOf(
                "address" to (address ?: "_"),
                "label" to (addressLabel ?: "-"),
                "detail" to addressDetail,
                "name" to (addressName ?: "-"),
                "phone" to (addressPhone ?: "-")
            )
            addressList.add(firstAddress)
        }
        renderAllAddresses()










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

        val addAddress = view.findViewById<TextView>(R.id.add_address)

        addAddress.setOnClickListener {
            val intent = Intent(requireContext(), CreateAddressActivity::class.java)
            addressResultLauncher.launch(intent)
        }


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




        return view
    }

    private fun selectStore(selectedIndex: Int) {
        for (i in checkImages.indices) {
            if (i == selectedIndex) {
                checkImages[i].setImageResource(R.drawable.checked)
            } else {
                checkImages[i].setImageResource(R.drawable.uncheck)
            }
        }

        val storeName = when (selectedIndex) {
            0 -> "Concat"
            1 -> "Gejayan"
            2 -> "Wonosari"
            else -> "Unknown Store"
        }

        Toast.makeText(requireContext(), "Teh Idaman $storeName", Toast.LENGTH_SHORT).show()


    }

    private fun renderAllAddresses() {
        addressContainer.removeAllViews()
        if (addressList.isEmpty()) {
            addressEmptyCard.visibility = View.VISIBLE
        } else {
            addressEmptyCard.visibility = View.GONE
            for (address in addressList) {
                val addressView = layoutInflater.inflate(R.layout.item_address, addressContainer, false)

                addressView.findViewById<TextView>(R.id.address_label).text = address["label"]
                addressView.findViewById<TextView>(R.id.address_detail).text = address["detail"]
                addressView.findViewById<TextView>(R.id.address_name_phone).text = "${address["name"]} - ${address["phone"]}"
                addressView.findViewById<TextView>(R.id.address).text = address["address"]

                addressContainer.addView(addressView)
            }
        }
    }




}