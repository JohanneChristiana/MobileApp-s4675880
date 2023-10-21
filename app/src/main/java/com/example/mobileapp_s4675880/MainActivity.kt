package com.example.mobileapp_s4675880

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    // Variable for Button
    private var usernameInput = ""
    private var passwordInput = ""

    // Retrofit Object
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://80fe-218-214-181-163.ngrok-free.app") // Base URL API using ngrok
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }
    // Getting the loginApi from Retrofit
    private val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    // Creating a Live Data (Implementing to observe pattern to observe response)
    private val loginResponseLiveData = MutableLiveData<LoginResponse?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Layout Resource

        // Response when login is successful
        loginResponseLiveData.observe (this) { // Observe any changes to LoginResponse
            it?.let{ response ->
                Toast.makeText(this, "${response.message}", Toast.LENGTH_LONG).show() // Length_long stays on screen longer
            }
        }

        // Initialize Button
        findViewById<EditText>(R.id.usernameInput).addTextChangedListener {
            usernameInput = it.toString()
        }

        findViewById<EditText>(R.id.passwordInput).addTextChangedListener {
            passwordInput = it.toString()
        }

        // Initialize Button
        findViewById<AppCompatButton>(R.id.btnSecondScreen).setOnClickListener {
            // Always use Dispatchers.Main when handling with Live Data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    // This Needs to be executed inside a Background Thread (That's why CaroutineScope)
                    loginResponseLiveData.value =  loginApi.login(username = usernameInput, password = passwordInput)
                } catch (e: Exception) {
                    loginResponseLiveData.value = LoginResponse(message = "Network call failed $e")
                }
            }
        }
    }
}