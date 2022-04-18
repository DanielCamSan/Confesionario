package edu.bo.confesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView

class Ayuda : AppCompatActivity() {
    private var expandableTextView: ExpandableListView? = null
    private var adapter = ExpandableTextViewAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        expandableTextView=findViewById(R.id.eTV)
        expandableTextView!!.setAdapter(adapter)
    }
}