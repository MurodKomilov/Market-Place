package com.example.marketplace.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marketplace.Models.Products
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentProductMenuBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProductMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product_menu, container, false)
        var binding = FragmentProductMenuBinding.bind(view)
        var productList = ArrayList<Products>()


        var product = arguments?.getString("key")
        if (!product.isNullOrEmpty()) {
            val productsList:ArrayList<Products> = Gson().fromJson(product, object : TypeToken<List<Products>>() {}.type)
            productList = productsList
            Log.d("Listlar1", "$productList")

        }
        Log.d("Listlar", "$product")



        return view
    }



}