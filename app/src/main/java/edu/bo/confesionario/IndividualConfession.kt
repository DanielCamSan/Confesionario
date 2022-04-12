package edu.bo.confesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IndividualConfession : AppCompatActivity() {
    private val backBtn: Button
        get() = findViewById(R.id.backButton)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_confession)
        backBtn.findViewById<Button>(R.id.backButton)
        backBtn.setOnClickListener {
            finish()
        }
    }
}