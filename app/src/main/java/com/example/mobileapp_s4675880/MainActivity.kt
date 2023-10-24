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

    //  Declare shared preferences
    private lateinit var sharedPreferences: SharedPreferences

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
        setContentView(R.layout.activity_main) // Layout Resource

        findViewById<EditText>(R.id.usernameInput).addTextChangedListener {
            username = it.toString()
        }

        findViewById<EditText>(R.id.passwordInput).addTextChangedListener {
            password = it.toString()
        }

        // Initializing EditTexts and our Button
        val userEdt = findViewById<EditText>(R.id.usernameInput)
        val passEdt = findViewById<EditText>(R.id.passwordInput)
        val signBtn = findViewById<Button>(R.id.btnSecondScreen)

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        // in shared prefs inside get string method
        // we are passing key value as USER_KEY and
        // default value is
        // set to null if not present.
        user = sharedpreferences.getString("USER_KEY", null)
        pass = sharedpreferences.getString("PASSWORD_KEY", null)

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

                                val editor = sharedpreferences.edit()
                                // below two lines will put values for
                                // user and pass in shared preferences.
                                editor.putString(USER_KEY, userEdt.text.toString())
                                editor.putString(PASSWORD_KEY, passEdt.text.toString())
                                editor.apply()

                                // Navigate to second screen activity
                                val intent1 = Intent(this@MainActivity, SecondScreenActivity::class.java)
                                startActivity(intent1)
                                finish()

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
                                finish()
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





