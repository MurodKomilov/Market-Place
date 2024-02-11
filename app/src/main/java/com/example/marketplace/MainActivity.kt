package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import com.example.marketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        fun getTextWatcher(editText: EditText, ){
            editText.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)

            editText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    editText.background.clearColorFilter()

                    editText.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_UP &&
                            editText.compoundDrawablesRelative.getOrNull(2) != null) {

                            val clearButtonPosition = editText.width - editText.paddingEnd
                            if (event.rawX >= (clearButtonPosition - editText.compoundDrawablesRelative[2].bounds.width())) {
                                editText.setText("")
                                return@setOnTouchListener true }
                        }
                        false
                    }

                }

                override fun afterTextChanged(s: Editable?) {
                    val text = s.toString()

                    if (text.contains(Regex("[^А-Яа-я]"))){
                        editText.background.setColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP)
                    } else binding.name.background.clearColorFilter()

                    editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0, 0, if (s.isNullOrEmpty()) 0 else R.drawable.clear_btn, 0)

                }
            })
        }

        getTextWatcher(binding.name)
        getTextWatcher(binding.lastName)





    }

}