package com.example.dots.fragmentsBestPromo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dots.R
import com.example.dots.detailPromo.PromoActivityIn1


class PromoFragmentIn : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_promo, container, false)

        //mencari promo layout
        val promoLayout2 = view.findViewById<LinearLayout>(R.id.promo_2)
        val promoLayout3 = view.findViewById<LinearLayout>(R.id.promo_3)
        val promoLayout4 = view.findViewById<LinearLayout>(R.id.promo_4)

        //mencari promo image
        val promoLayoutImage2 = promoLayout2.findViewById<ImageView>(R.id.promoImage)
        val promoLayoutImage3 = promoLayout3.findViewById<ImageView>(R.id.promoImage)
        val promoLayoutImage4 = promoLayout4.findViewById<ImageView>(R.id.promoImage)

        //mencari promo title
        val promoLayoutTitle2 = promoLayout2.findViewById<TextView>(R.id.promoTitle)
        val promoLayoutTitle3 = promoLayout3.findViewById<TextView>(R.id.promoTitle)
        val promoLayoutTitle4 = promoLayout4.findViewById<TextView>(R.id.promoTitle)

        //mencari promo description
        val promoLayoutDesc2 = promoLayout2.findViewById<TextView>(R.id.promoDescription)
        val promoLayoutDesc3 = promoLayout3.findViewById<TextView>(R.id.promoDescription)
        val promoLayoutDesc4 = promoLayout4.findViewById<TextView>(R.id.promoDescription)

        //setting promo image
        promoLayoutImage2.setImageResource(R.drawable.promo_2)
        promoLayoutImage3.setImageResource(R.drawable.promo_3)
        promoLayoutImage4.setImageResource(R.drawable.promo_4)

        //setting promo title
        promoLayoutTitle2.text = getString(R.string.promo_title1)
        promoLayoutTitle3.text = getString(R.string.promo_title2)
        promoLayoutTitle4.text = getString(R.string.promo_title3)

        //setting promo description
        promoLayoutDesc2.text = getString(R.string.promo_desc1)
        promoLayoutDesc3.text = getString(R.string.promo_desc2)
        promoLayoutDesc4.text = getString(R.string.promo_desc3)



        //navigasi promo 2
        promoLayout2.setOnClickListener{
            val intent = Intent(requireContext(), PromoActivityIn1::class.java)

            //mengirim layout promo
            intent.putExtra("GAMBAR_PROMO", R.drawable.promo_2)
            intent.putExtra("DISKON_PROMO", R.string.diskon_40)
            intent.putExtra("JUDUL_PROMO", R.string.diskon_5_5_gila_gilaan)
            intent.putExtra("EXPIRED_DATE", R.string.berlaku_sampai_6_mei_2025)

            //deskripsi promo
            intent.putExtra("DESKRIPSI_1", R.string.promo_desc_1)



            //syarat dan ketentuan
            intent.putExtra("TOS_1", R.string.tos_v1)
            intent.putExtra("TOS_2", R.string.tos_v2)
            intent.putExtra("TOS_3", R.string.tos_v3)

            //gambar item promo
            intent.putExtra("ITEM_PROMO1", R.drawable.tt_original)
            intent.putExtra("ITEM_PROMO2", R.drawable.ts_orange)
            intent.putExtra("ITEM_PROMO3", R.drawable.ss_strawberry)

            //nama item promo
            intent.putExtra("NAMA_ITEM1", R.string.thai_tea)
            intent.putExtra("NAMA_ITEM2", R.string.lemon_tea)
            intent.putExtra("NAMA_ITEM3", R.string.strawberry_squash)

            startActivity(intent)
        }


        //navigasi promo 3
        promoLayout3.setOnClickListener{
            val intent = Intent(requireContext(), PromoActivityIn1::class.java)

            //mengirim layout promo
            intent.putExtra("GAMBAR_PROMO", R.drawable.promo_3)
            intent.putExtra("DISKON_PROMO", R.string.diskon_60)
            intent.putExtra("JUDUL_PROMO", R.string.promo_spesial_pancasila)
            intent.putExtra("EXPIRED_DATE", R.string.berlaku_sampai_21_mei_2025)

            //deskripsi promo
            intent.putExtra("DESKRIPSI_1", R.string.promo_desc_2)



            //syarat dan ketentuan
            intent.putExtra("TOS_1", R.string.tos_v1)
            intent.putExtra("TOS_2", R.string.tos_v2)
            intent.putExtra("TOS_3", R.string.tos_v3)

            //gambar item promo
            intent.putExtra("ITEM_PROMO1", R.drawable.tt_original)
            intent.putExtra("ITEM_PROMO2", R.drawable.tt_oreo)
            intent.putExtra("ITEM_PROMO3", R.drawable.tt_choco)

            //nama item promo
            intent.putExtra("NAMA_ITEM1", R.string.thai_tea_original)
            intent.putExtra("NAMA_ITEM2", R.string.thai_tea_oreo)
            intent.putExtra("NAMA_ITEM3", R.string.thai_tea_choco)

            startActivity(intent)
        }

        //navigasi promo 4
        promoLayout4.setOnClickListener{
            val intent = Intent(requireContext(), PromoActivityIn1::class.java)

            //mengirim layout promo
            intent.putExtra("GAMBAR_PROMO", R.drawable.promo_4)
            intent.putExtra("DISKON_PROMO", R.string.diskon_matcha)
            intent.putExtra("JUDUL_PROMO", R.string.promo_jumat)
            intent.putExtra("EXPIRED_DATE", R.string.berlaku_setiap_jumat)

            //deskripsi promo
            intent.putExtra("DESKRIPSI_1", R.string.promo_desc_3)



            //syarat dan ketentuan
            intent.putExtra("TOS_1", R.string.tos_v1)
            intent.putExtra("TOS_2", R.string.tos_v2)
            intent.putExtra("TOS_3", R.string.tos_v3)

            //gambar item promo
            intent.putExtra("ITEM_PROMO2", R.drawable.ms_matcha)

            //nama item promo
            intent.putExtra("NAMA_ITEM2", R.string.matcha_creamy)

            startActivity(intent)
        }







        // Inflate the layout for this fragment
        return view



    }


}