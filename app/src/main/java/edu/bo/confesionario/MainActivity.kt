package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val btn: Button
        get() = findViewById(R.id.button)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        btn.setOnClickListener{
            val intent = Intent(this, IndividualConfession::class.java)
            startActivity(intent)
        }
=======
        val intent = Intent(this, Publications::class.java)
        // To pass any data to next activity
        //intent.putExtra("keyIdentifier", value)
        // start your next activity
        startActivity(intent)
        //app:menu="@menu/publications_menu"
>>>>>>> alvaro-publications
    }
}