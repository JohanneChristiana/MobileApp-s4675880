package com.example.mobileapp_s4675880

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class SecondScreenActivity : AppCompatActivity() {



//    private fun SharedPreferences.putString(user: String, pass: String) {
//        fun SharedPreferences.commit() {
//        }
//    }
//
//    val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
//    val username = sharedPreferences.getString("username", null)

    // Variable for Button
    private var button: AppCompatButton? = null

    //Intent pt.1
    private val intentToNavigateToMainActivity by lazy {
        Intent(this, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen) // Layout Resource

        // Initialize Button
        button = findViewById(R.id.btn_back_dashboard) as? AppCompatButton;

        button?.setOnClickListener {
            Log.d("App", "Button Clicked")

            //Intent pt.2
            startActivity(intentToNavigateToMainActivity)
        }
        }

    }


