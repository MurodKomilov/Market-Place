package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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




        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Проверка всех полей на валидность и активация кнопки "Войти"
                val firstNameValid = validateCyrillicText(binding.name.text.toString())
                val lastNameValid = validateCyrillicText(binding.lastName.text.toString())
                val phoneNumberValid = validatePhoneNumber(binding.phoneNumber.text.toString())

                if(firstNameValid && lastNameValid && phoneNumberValid){
                    binding.logIn.isEnabled
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.name.addTextChangedListener(textWatcher)
        binding.lastName.addTextChangedListener(textWatcher)
        binding.phoneNumber.addTextChangedListener(textWatcher)

    }

    // Валидация текста на кириллицу
    private fun validateCyrillicText(text: String): Boolean {
        return text.matches("[А-Яа-я ]+".toRegex())
    }

    // Валидация номера телефона
    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.matches("\\+7 \\d{3} \\d{3}-\\d{2}-\\d{2}".toRegex())
    }

}