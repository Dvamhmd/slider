package com.example.dots.fragmentsBestPromo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.dots.R


class PromoFragment : Fragment() {


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



        // Inflate the layout for this fragment
        return view
    }


}