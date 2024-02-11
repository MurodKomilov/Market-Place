package com.example.marketplace

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import com.example.marketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logIn.isEnabled = false

        setUpEditText(binding.name)
        setUpEditText(binding.lastName)
        binding.logIn.setOnClickListener {
            Toast.makeText(this, "Bosildi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpEditText(editText: EditText) {
        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            @SuppressLint("ClickableViewAccessibility")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText.background.clearColorFilter()

                editText.setOnTouchListener { _, event ->
                    if (event.action == MotionEvent.ACTION_UP &&
                        editText.compoundDrawablesRelative.getOrNull(2) != null) {

                        val clearButtonPosition = editText.width - editText.paddingEnd
                        if (event.rawX >= (clearButtonPosition - editText.compoundDrawablesRelative[2].bounds.width())) {
                            editText.setText("")
                            return@setOnTouchListener true
                        }
                    }
                    false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()

                if (text.contains(Regex("[^А-Яа-я]"))) {
                    editText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
                } else {
                    editText.background.clearColorFilter()
                }

                editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, 0, if (s.isNullOrEmpty()) 0 else R.drawable.clear_btn, 0
                )

                checkEditTexts()
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun checkEditTexts() {
        val nameFilled = binding.name.text.isNotEmpty()
        val lastNameFilled = binding.lastName.text.isNotEmpty()

        if (nameFilled){
            binding.logIn.setBackgroundColor(binding.logIn.context.resources.getColor(R.color.btn_background))
            binding.logIn.isEnabled = true
        } else {
            binding.logIn.setBackgroundColor(binding.logIn.context.resources.getColor(R.color.btn_background2))
            binding.logIn.isEnabled = false
        }


    }
}


