package com.example.marketplace

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marketplace.Fragments.ProductMenuFragment
import com.example.marketplace.Interfaces.Communicator
import com.example.marketplace.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(),Communicator {
    lateinit var binding:ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMenu.setupWithNavController(findNavController(R.id.fragment))
    }

    @SuppressLint("CommitTransaction")
    override fun passDataCom(data: String) {
        val bundle = Bundle()
        bundle.putString("message", data)

        var productMenuFragment = ProductMenuFragment()
        productMenuFragment.arguments = bundle
        this.supportFragmentManager.beginTransaction().commit()
        Log.d("Listlar", "Activity: $data")
    }
}