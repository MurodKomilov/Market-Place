package com.example.marketplace.Fragments

import android.nfc.Tag
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.Adapters.TagsAdapter
import com.example.marketplace.Models.Tags
import com.example.marketplace.R
import com.example.marketplace.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {
    var tagList = ArrayList<Tags>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val binding = FragmentCatalogBinding.bind(view)

        var adapter = activity?.let { ArrayAdapter.createFromResource(it.baseContext,R.array.sortNames,android.R.layout.simple_spinner_item) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sortSpinner.adapter = adapter
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Выбрано: $selectedItem", Toast.LENGTH_SHORT).show()
                // Ваш код для реакции на выбор элемента из Spinner
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Действия, если не выбран ни один элемент из списка
            }
        }


        addData()
        val tagsAdapter = TagsAdapter(tagList)
        binding.tags.adapter = tagsAdapter

        binding.tags.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    fun addData(){
        tagList.add(Tags("Смотреть все"))
        tagList.add(Tags("Лицо"))
        tagList.add(Tags("Тело"))
        tagList.add(Tags("Загар"))
        tagList.add(Tags("Маски"))
    }

}