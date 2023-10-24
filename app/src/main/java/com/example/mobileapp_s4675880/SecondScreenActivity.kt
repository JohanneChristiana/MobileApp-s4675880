package com.example.mobileapp_s4675880

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class SecondScreenActivity : AppCompatActivity() {

    // Variable for Button
    private var button: AppCompatButton? = null

    //Intent pt.1
    private val intentToNavigateToMainActivity by lazy {
        Intent(this, MainActivity::class.java)
    }

    // creating constant keys for shared preferences.
    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val USER_KEY = "user_key"
        const val PASSWORD_KEY = "password_key"
    }

    // variable for shared preferences.
    private lateinit var sharedpreferences: SharedPreferences
    private var user: String? = null
    private var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen) // Layout Resource

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        // getting data from shared prefs and
        // storing it in our string variable.
        user = sharedpreferences.getString(USER_KEY, null)
        pass = sharedpreferences.getString(PASSWORD_KEY, null)

        // initializing our textview and button.
        val userTextView = findViewById<TextView>(R.id.userTextView)
        userTextView.text = "Username: $user"
        val passTextView = findViewById<TextView>(R.id.passTextView)
        passTextView.text = "Password: $pass"

        val logoutBtn = findViewById<Button>(R.id.btn_logout)
        logoutBtn.setOnClickListener {
            // calling method to edit values in shared prefs.
            val editor = sharedpreferences.edit()

            // below line will clear
            // the data in shared prefs.
            editor.clear()

            // below line will apply empty
            // data to shared prefs.
            editor.apply()

            // starting mainactivity after
            // clearing values in shared preferences.
            val i = Intent(this@SecondScreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}




