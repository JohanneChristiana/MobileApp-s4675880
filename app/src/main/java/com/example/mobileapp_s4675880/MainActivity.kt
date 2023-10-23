package com.example.mobileapp_s4675880

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    // Variable for input
    private var username = ""
    private var password = ""
    private var button: AppCompatButton? = null

    // Retrofit Object
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://f6b4-131-170-5-4.ngrok-free.app") // Base URL API using ngrok
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }

    // Getting the loginApi from Retrofit
    private val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    // Creating a Live Data (Implementing to observe pattern to observe response)
    private val loginResponseLiveData = MutableLiveData<LoginResponse?>(null)

    //Intents
    private val intentToNavigateToCourseActivity by lazy {Intent(this, CourseActivity::class.java)}
    private val intentToNavigateToSecondScreenActivity by lazy {Intent(this, SecondScreenActivity::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Layout Resource

        findViewById<EditText>(R.id.usernameInput).addTextChangedListener {
            username = it.toString()
        }

        findViewById<EditText>(R.id.passwordInput).addTextChangedListener {
            password = it.toString()
        }

        // Initialize Button for Login
        findViewById<Button>(R.id.btnSecondScreen).setOnClickListener {
            // Always use Dispatchers.Main when handling with Live Data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    loginResponseLiveData.value = loginApi.login(username = username, password = password)

                    loginResponseLiveData.observe(this@MainActivity) { // Observe any changes to LoginResponse
                        it?.let{ response ->
                            if (loginResponseLiveData.value?.isSuccessful == true) {
                                // Navigate to new activity
                                startActivity(intentToNavigateToSecondScreenActivity)
                            } else { // Show a toast message if the login response is not successful
                                Toast.makeText(
                                    this@MainActivity,
                                    "${response.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                } catch (e: Exception) {
                    loginResponseLiveData.value = LoginResponse(message = "Network call failed $e")
                }
            }
        }

        // Initialize Button for CourseActivity
        findViewById<Button>(R.id.btnCourseActivity).setOnClickListener {
            // Always use Dispatchers.Main when handling with Live Data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    loginResponseLiveData.value = loginApi.login(username = username, password = password)

                    loginResponseLiveData.observe(this@MainActivity) { // Observe any changes to LoginResponse
                        it?.let{ response ->
                            if (loginResponseLiveData.value?.isSuccessful == true) {
                                // Navigate to new activity
                                startActivity(intentToNavigateToCourseActivity)
                            } else { // Show a toast message if the login response is not successful
                                Toast.makeText(
                                    this@MainActivity,
                                    "${response.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                } catch (e: Exception) {
                    loginResponseLiveData.value = LoginResponse(message = "Network call failed $e")
                }
            }
        }
//        // Initialize Button for RecyclerView
//        button = findViewById(R.id.btnCourseActivity) as? AppCompatButton;
//
//        button?.setOnClickListener {
//            Log.d("App", "Button Clicked")
//
//            //Intent pt.2
//            startActivity(intentToNavigateToCourseActivity)
//        }
    }
}

