package com.example.mobileapp_s4675880

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp_s4675880.models.ListItemDataModel
import com.example.mobileapp_s4675880.recyclerviewcomponents.MyRecyclerviewAdapter

class CourseActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.homeScreenRecyclerView)
    }

    // Variable for Button
    private var button: AppCompatButton? = null

    //Intent pt.1
    private val intentToNavigateToMainActivity by lazy {
        Intent(this, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2) // Layout Resource

        recyclerView.adapter = MyRecyclerviewAdapter(
            context = this,
            dataList = listOf(
                ListItemDataModel("Sculpture", "ART300", "Three-dimensional art "),
                ListItemDataModel("Painting", "ART202", "Painting styles and techniques"),
                ListItemDataModel("Ceramics", "ART204", "Art of clay shaping and glazing."),
                ListItemDataModel("Drawing", "ART302", "Hone skills in drawing"),
                ListItemDataModel("Creative Arts", "VA101-CART", "Study of creating and performing"),
                ListItemDataModel("Calligraphy", "VA203-CALL", "Beautiful Handwriting"),
                ListItemDataModel("Abstract", "ART205", "Abstract Expressionism"),
                ListItemDataModel("Digital Art", "VA105-CAD", "Computer generated"),
                ListItemDataModel("3D Art", "ART223", "Hands-on 3D  Art class"),
                ListItemDataModel("Art History", "VA106-AHY", "Arts-What happened before"),
                ListItemDataModel("PrintMaking", "ART342", "World of printmaking"),
                ListItemDataModel("Art Analysis", "ART307", "Design critical thinking skills"),
                ListItemDataModel("Art Technology", "ART602", "Intersection of art and technology"),
                ListItemDataModel("Public Arts", "ART308", "Beautify and engage communities"),
                ListItemDataModel("Contemporary Arts", "ART310", "Modern and contemporary art movements"),
                ListItemDataModel("Pop Art", "VA107-PAT", "Pop Art and beyond")
            )
        )
        // Initialize Button
        button = findViewById(R.id.btn_back_recycler) as? AppCompatButton;

        button?.setOnClickListener {
            Log.d("App", "Button Clicked")

            //Intent pt.2
            startActivity(intentToNavigateToMainActivity)
        }
    }
}