package com.example.marketplace

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.SharedPreferences.MyPreferences
import com.example.marketplace.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.santalu.maskara.widget.MaskEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var userList = ArrayList<User>()
    var JSON_KEY = "JSON_KEY"
    var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.addTextChangedListener(textWatcher)
        binding.lastName.addTextChangedListener(textWatcher)
        binding.phoneNumber.addTextChangedListener(textWatcher)

        var sharedPreferences = MyPreferences(this)
        var readGson = sharedPreferences.readData(JSON_KEY,)

        binding.logIn.setOnClickListener {
            var typeOfObjectList = object : TypeToken<ArrayList<User>>(){}.type
            val name = binding.name.text.toString()
            val lastName = binding.lastName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            userList.add(User(name,lastName,phoneNumber))

            val jsonText = gson.toJson(userList,typeOfObjectList)

            sharedPreferences.putData(JSON_KEY,jsonText)
            Log.d("SharedPerferences", "onCreate: $userList")

            startActivity(Intent(this,MenuActivity::class.java))

        }

    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            icClear(binding.name)           // Запускает функцию иконки
            icClear(binding.lastName)       // Запускает функцию иконки
            icClear(binding.phoneNumber)    // Запускает функцию иконки
        }

        override fun afterTextChanged(s: Editable?) {

            cyrillicValidation(binding.lastName)    // Проверка Валидацию на Кириллице
            cyrillicValidation(binding.name)        // Проверка Валидацию на Кириллице
            maskEditText(binding.phoneNumber)       // Проверка Валидацию на Кириллице

            val text1 = binding.name.text.toString()
            val text2 = binding.lastName.text.toString()

            val isNameCyrillic = checkCyrillicForBtn(text1)          // Проверка EditText на Активацию кнопки LogIn
            val isLastNameCyrillic = checkCyrillicForBtn(text2)      // Проверка EditText на Активацию кнопки LogIn
            val phoneNumber = maskEditText(binding.phoneNumber)      // Проверка MaskEditText на Активацию кнопки LogIn

            if (isNameCyrillic && isLastNameCyrillic && phoneNumber){     // Активация кнопки LogIn
                binding.logIn.background.setColorFilter(0xFFD62F89.toInt(), PorterDuff.Mode.SRC_ATOP)
                binding.logIn.isEnabled = true
            } else{
                binding.logIn.background.setColorFilter(0xFFFF8AC9.toInt(), PorterDuff.Mode.SRC_ATOP)
                binding.logIn.isEnabled = false
            }
        }
    }

    private fun checkCyrillicForBtn(text: String): Boolean {  // Логика для проверки EditText на Активацию кнопки LogIn
        val cyrillicRegex = Regex("[А-Яа-яЁё]+")
        return cyrillicRegex.matches(text)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun icClear(editText:EditText) {

        editText.setOnTouchListener { _, event ->          // Логика иконки крестика
            if (event.action == MotionEvent.ACTION_UP &&
                editText.compoundDrawablesRelative.getOrNull(2) != null
            ) {
                val clearButtonPosition = editText.width - editText.paddingEnd
                if (event.rawX >= (clearButtonPosition - editText.compoundDrawablesRelative[2].bounds.width())) {
                    editText.setText("")
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    fun cyrillicValidation(editText:EditText){     // Валидация на Кириллицу, Если в EditText печатается на крилице, то все хорошо
        val text = editText.text.toString()        // если нет то EditText покраснеет

        if (text.contains(Regex("[^А-Яа-я]"))) {
            editText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(       // Показывает иконку крестика
                0, 0, if (text.isNullOrEmpty()) 0 else R.drawable.ic_clear, 0)
        } else {
            editText.background.clearColorFilter()
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(       // Показывает иконку крестика
                0, 0, if (text.isNullOrEmpty()) 0 else R.drawable.ic_clear, 0)
        }
    }


    fun maskEditText(mask: MaskEditText):Boolean{   // Логика для проверки MaskEditText на Активацию кнопки LogIn
        var raw = mask.text.isNullOrEmpty()
        var isDone = mask.isDone

        if (raw) return false                       // Если MaskEditText Пусто то возвращяет false

        if (isDone){                                // Если MaskEditText Полностью заполнена то возвращяет true
            mask.background.clearColorFilter()
            return true
        } else{
            if (raw) {
                mask.background.clearColorFilter()
            } else{
                mask.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
                mask.setCompoundDrawablesRelativeWithIntrinsicBounds(   // Показывает иконку крестика
                    0, 0, if (mask.text.toString().isNullOrEmpty()) 0 else R.drawable.ic_clear, 0)
            }
        }
        return false
    }


}