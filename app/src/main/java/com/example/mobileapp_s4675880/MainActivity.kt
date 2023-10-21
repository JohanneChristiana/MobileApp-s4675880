package com.example.mobileapp_s4675880

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    // Variable for Button
    private var usernameInput = ""
    private var passwordInput = ""

    // Retrofit Object
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://catfact.ninja") // Base URL
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Layout Resource

        // Initialize Button
        findViewById<EditText>(R.id.usernameInput).addTextChangedListener {
            usernameInput = it.toString()
        }

        findViewById<EditText>(R.id.passwordInput).addTextChangedListener {
            passwordInput = it.toString()
        }

        // Initialize Button
        findViewById<EditText>(R.id.btnSecondScreen).setOnClickListener {
            //Retrofit call here

        }
    }
}


//    //Intent pt.1
//    private val intentToNavigateToSecondScreen by lazy {
//        Intent(this, SecondScreenActivity::class.java)
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // Layout Resource
//
//        // Initialize Button
//        button = findViewById(R.id.btnSecondScreen) as? AppCompatButton;
//
//        button?.setOnClickListener {
//            Log.d("App", "Button Clicked")
//
//            //Intent pt.2
//            startActivity(intentToNavigateToSecondScreen)
//        }
//
//    }
//}