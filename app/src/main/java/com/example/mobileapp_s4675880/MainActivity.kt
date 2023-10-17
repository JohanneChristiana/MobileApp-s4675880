package com.example.mobileapp_s4675880

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    // Variable for Button
    private var button: AppCompatButton? = null

    //Intent pt.1
    private val intentToNavigateToSecondScreen by lazy {
        Intent(this, SecondScreenActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Layout Resource

        // Initialize Button
        button = findViewById(R.id.btnSecondScreen) as? AppCompatButton;

        button?.setOnClickListener {
            Log.d("App", "Button Clicked")

            //Intent pt.2
            startActivity(intentToNavigateToSecondScreen)
        }

    }
}