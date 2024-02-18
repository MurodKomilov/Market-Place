package com.example.marketplace.Fragments

import android.content.Context
import android.content.res.Resources
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.Adapters.ClickListener
import com.example.marketplace.Adapters.ProductAdapter
import com.example.marketplace.Adapters.TagsAdapter
import com.example.marketplace.Models.Products
import com.example.marketplace.Models.Tags
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentCatalogBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class CatalogFragment : Fragment() {
    var tagList = ArrayList<Tags>()
    var productsList = ArrayList<Products>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val binding = FragmentCatalogBinding.bind(view)

            var adapter = activity?.let { ArrayAdapter.createFromResource(it.baseContext,R.array.sortNames,android.R.layout.simple_spinner_item) }
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.sortSpinner.adapter = adapter
            binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {     // Функция для обоаботки кнопок сортировки

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()  // Функция для выбранного

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {              // Функция для невыбранного

                }
            }


        addData()
        val tagsAdapter = TagsAdapter(tagList,object: ClickListener{
            override fun onClickListener(item: Tags) {                                  // Функция для выбранного Тэга

            } })
        binding.tags.adapter = tagsAdapter
        binding.tags.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val items = loadItemsFromJson(requireContext())
        val productAdapter = ProductAdapter(items)
        binding.products.adapter = productAdapter


        return view
    }

    fun addData(){
        tagList.add(Tags("Смотреть все"))
        tagList.add(Tags("Лицо"))
        tagList.add(Tags("Тело"))
        tagList.add(Tags("Загар"))
        tagList.add(Tags("Маски"))
    }
    fun loadItemsFromJson(context: Context): List<Products> {
        val inputStream = context.resources.openRawResource(R.raw.item)
        val json = inputStream.bufferedReader().use { it.readText() }
        val itemType = object : TypeToken<List<Products>>() {}.type
        return Gson().fromJson(json, itemType)
    }



}