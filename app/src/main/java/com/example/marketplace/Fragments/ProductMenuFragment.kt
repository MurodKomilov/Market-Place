package com.example.marketplace.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.marketplace.Base.BaseFragment
import com.example.marketplace.Interfaces.Communicator
import com.example.marketplace.Models.Products
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentProductMenuBinding
import com.example.myapplication.ViewPagerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProductMenuFragment : BaseFragment<FragmentProductMenuBinding>(FragmentProductMenuBinding::inflate){


    var product:String? = ""
    var image:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreate() {

        product = arguments?.getString("product")
        var productList:ArrayList<Products> = Gson().fromJson(product, object : TypeToken<ArrayList<Products>>() {}.type)
        Log.d("Listlar", "$productList ")
        image = arguments?.getString("imageList")
        var imageList:ArrayList<Int> = Gson().fromJson(image, object : TypeToken<ArrayList<Int>>() {}.type)


        binding.apply {
            imageVp.adapter = ViewPagerAdapter(imageList)
            imageVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            indicator.setViewPager(binding.imageVp)

            productName.text = productList[0].title
            subtitle.text = productList[0].subtitle
            newPrice.text = "${productList[0].price.priceWithDiscount} ₽"
            newPrice2.text = "${productList[0].price.priceWithDiscount} ₽"
            oldPrice.text = "${productList[0].price.price} ₽"
            oldPrice2.text = "${productList[0].price.price} ₽"
            discount.text = "${productList[0].price.discount} ₽"
            productName2.text = productList[0].title
            description2.text = productList[0].description
            articleNumber.text = productList[0].info[0].value
            area.text = productList[0].info[1].value
            country.text = productList[0].info[2].value
            structure.text = productList[0].ingredients
        }






    }


}