package com.example.marketplace.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentProductMenuBinding


class ProductMenuFragment : Fragment() {

    var displayMessage:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product_menu, container, false)
        var binding = FragmentProductMenuBinding.bind(view)

//        displayMessage = arguments?.getString("message").toString()
//
//        binding.productNameA.text = displayMessage





        return view
    }



}