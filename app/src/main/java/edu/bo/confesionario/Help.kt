package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageButton

class Help : AppCompatActivity() {
    private var expandableTextView: ExpandableListView? = null
    private var adapter = ExpandableTextViewAdapter(this)

    private val btnBack : Button
        get() = findViewById(R.id.go_back_btn)

    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        expandableTextView=findViewById(R.id.eTV)
        expandableTextView!!.setAdapter(adapter)
        btnBack.findViewById<Button>(R.id.go_back_btn)
        btnBack.setOnClickListener{
            finish()
        }
        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        toolBarLogoutBtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

    }
}