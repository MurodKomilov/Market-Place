package com.example.marketplace.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.Adapters.ClickListener
import com.example.marketplace.Adapters.ProductAdapter
import com.example.marketplace.Adapters.TagsAdapter
import com.example.marketplace.Interfaces.Communicator
import com.example.marketplace.Models.Products
import com.example.marketplace.Models.Tags
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentCatalogBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CatalogFragment : Fragment(){
    var tagList = ArrayList<Tags>()
    lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val binding = FragmentCatalogBinding.bind(view)

        addData()
        val tagsAdapter = TagsAdapter(tagList,object: ClickListener{
            override fun onClickListener(item: Tags) {                                  // Функция для выбранного Тэга

            } })
        binding.tags.adapter = tagsAdapter
        binding.tags.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        var jsonData = loadItemsFromJson(requireContext())

        val sortName = listOf("", "По популярности", "По уменьшению цены", "По возрастанию цены")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sortName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sortSpinner.adapter = adapter
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {     // Функция для обоаботки кнопок сортировки

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()  // Функция для выбранного

                when (selectedItem) {
                    "" -> {
                        clickItem1(jsonData,binding)
                    }
                    "По популярности" -> clickItem(jsonData,binding)
                    "По уменьшению цены" -> clickItem(jsonData,binding)
                    "По возрастанию цены" ->  clickItem(jsonData,binding)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {              // Функция для невыбранного

            }
        }



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

    fun clickItem1(jsonData: List<Products>, binding: FragmentCatalogBinding){
        var productAdapter = ProductAdapter(jsonData, object: ProductAdapter.ClickListener{
            override fun onClickItem(product: Products, imageList: ArrayList<Int>) {
                var productList = ArrayList<Products>()
                productList.add(product)
                val bundle = Bundle()
                bundle.putString("product", Gson().toJson(productList))
                bundle.putString("imageList", Gson().toJson(imageList))

                findNavController().navigate(R.id.productMenuFragment,bundle)


            } })
        binding.products.adapter = productAdapter
    }

    fun clickItem(jsonData:List<Products>,binding:FragmentCatalogBinding){
        var newItems = jsonData.sortedByDescending { it.feedback.rating }
        var productAdapter = ProductAdapter(newItems, object: ProductAdapter.ClickListener{
            override fun onClickItem(product: Products, imageList: ArrayList<Int>) {
                var productList = ArrayList<Products>()
                productList.add(product)
                val bundle = Bundle()
                bundle.putString("product", Gson().toJson(productList))
                bundle.putString("imageList", Gson().toJson(imageList))

                findNavController().navigate(R.id.productMenuFragment,bundle)
            } })
        binding.products.adapter = productAdapter
    }

}