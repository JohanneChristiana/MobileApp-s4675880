package com.example.mobileapp_s4675880

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.mobileapp_s4675880.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    // Variable for input
    private var username = ""
    private var password = ""

    // Store data using Shared Preferences

    //binding
    private lateinit var binding: ActivityMainBinding

    //  Declare shared preferences
    private lateinit var sharedPreferences: SharedPreferences

//    private var SharedPreferences.putString(userTextView: String, passTextView: String)
//    private var sharedPreferences: SharedPreferences? = null

    // Retrofit Object
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://0f6d-218-214-181-163.ngrok-free.app") // Base URL API using ngrok
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
        //Binding pt.2
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main) // Layout Resource


        findViewById<EditText>(R.id.usernameInput).addTextChangedListener {
            username = it.toString()
        }

        findViewById<EditText>(R.id.passwordInput).addTextChangedListener {
            password = it.toString()
        }

//        val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
//        val userSave = sharedPreferences.putString("user", username)
//        val textView: TextView = findViewById(R.id.user)

        // Retrieve the username and password from the shared preferences file.
//        sharedPreferences.putString("username", "password");

        // Initialize Button for Login
        findViewById<Button>(R.id.btnSecondScreen).setOnClickListener {
            // Always use Dispatchers.Main when handling with Live Data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    loginResponseLiveData.value = loginApi.login(username = username, password = password)

                    loginResponseLiveData.observe(this@MainActivity) { // Observe any changes to LoginResponse
                        it?.let{ response ->
                            // If the property is not null and the login was successful...
                            if (loginResponseLiveData.value?.isSuccessful == true) {
                                // Save the login state in shared preferences, -> Initialize SharedPreferences
                                val sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

                                // Whatever the text user will write in usernameInput it will be stored in user variable
                                binding.saveButton.setOnClickListener{
                                    val user = binding.usernameInput.text.toString()
                                    val pass = binding.passwordInput.text.toString()
                                    //access mode, the preference can be modified or edited
                                    val sharedEdit = sharedPreferences.edit()
                                    // store preference using a key
                                    sharedEdit.putString("user", username).apply()
                                    sharedEdit.putString("pass", password).apply()

                                    binding.usernameInput.text.clear()
                                    binding.passwordInput.text.clear()

                                }
                                binding.saveButton.setOnClickListener {
                                    // To retrieve the data use get
                                    val storedUser = sharedPreferences.getString(
                                        "user",
                                        "")
                                    val storedPass = sharedPreferences.getString(
                                        "pass",
                                        "") //null, it will add a default value, that will be returned if there is not value associated with key note
                                    val username = sharedPreferences.getString("username", "")
                                    val password = sharedPreferences.getString("password", "")
                                    binding.usernameInput.text = "${storedUser}"
                                    binding.passwordInput.text = "${storedPass}"
                                }

                                // Navigate to second screen activity
                                val intent1 = Intent(this@MainActivity, SecondScreenActivity::class.java)
                                startActivity(intent1)

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

        // Initialize Button for RecyclerView / CourseActivity
        findViewById<Button>(R.id.btnCourseActivity).setOnClickListener {
            // Always use Dispatchers.Main when handling with Live Data
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    loginResponseLiveData.value = loginApi.login(username = username, password = password)

                    loginResponseLiveData.observe(this@MainActivity) { // Observe any changes to LoginResponse
                        it?.let{ response ->
                            if (loginResponseLiveData.value?.isSuccessful == true) {
                                // Navigate to new activity
                                val intent2= Intent(this@MainActivity, CourseActivity::class.java)
                                startActivity(intent2)
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
    }
}





