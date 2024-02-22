package com.example.marketplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.marketplace.Models.Products
import com.example.marketplace.databinding.ActivityProductBinding
import com.example.myapplication.ViewPagerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val product = intent.getStringExtra("productList")
        val productList: ArrayList<Products> = Gson().fromJson(product, object : TypeToken<ArrayList<Products>>() {}.type)

        val image = intent.getStringExtra("imageList")
        val imageList: ArrayList<Int> = Gson().fromJson(image, object : TypeToken<ArrayList<Int>>() {}.type)

        binding.imageVpA.adapter = ViewPagerAdapter(imageList)
        binding.imageVpA.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicatorA.setViewPager(binding.imageVpA)

        binding.productNameA.text = productList[0].title


    }
}