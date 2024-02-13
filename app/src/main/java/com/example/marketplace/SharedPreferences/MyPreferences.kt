package com.example.marketplace.SharedPreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

    class MyPreferences(context: Context) {

        private var sharedPreferences = context.getSharedPreferences("Key", AppCompatActivity.MODE_PRIVATE)
        private val edit = sharedPreferences.edit()

        fun putData(key: String, str:String){
            edit.putString(key, str)
            edit.apply()
        }

        fun readData(key: String):String{
            return sharedPreferences.getString(key, "Not Found !") ?:""
        }
    }

