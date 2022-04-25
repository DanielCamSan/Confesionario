package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.adapter.CommentAdapter
import java.time.LocalDate

class IndividualConfession : AppCompatActivity() {
    private val backBtn: Button
        get() = findViewById(R.id.backButton)
    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)
    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)
    private val userNameTxt : TextView
        get() = findViewById(R.id.userName)
    private val confessionNameTxt : TextView
        get() = findViewById(R.id.confessionName)
    private val confessionBodyTxt : TextView
        get() = findViewById(R.id.confessionBody)
    private val confessionDateTxt : TextView
        get() = findViewById(R.id.confessionDate)
    private val confessionNumberTxt : TextView
        get() = findViewById(R.id.confessionNumber)
    private val confessionCategoryTxt : TextView
        get() = findViewById(R.id.categoryName)
    private  lateinit var publicationData: Publication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_confession)
        backBtn.findViewById<Button>(R.id.backButton)
        backBtn.setOnClickListener {
            finish()
        }
        initRecyclerView()

        toolBarLogoutBtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }
        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }
        // get Data from intent bundle

        val bundle = intent.extras
        var category = bundle!!.getString("category")
        val number = bundle!!.getString("number")
        val title = bundle!!.getString("title")
        val date = LocalDate.parse(bundle!!.getString("date"))
        val description = bundle!!.getString("description")
        val userName = bundle!!.getString("userName")

        publicationData = Publication(category, number, title, description, date, userName)

        fillTextViews()
    }
    private fun fillTextViews(){
        userNameTxt.text = publicationData.userName
        confessionCategoryTxt.text = publicationData.category
        confessionNumberTxt.text = publicationData.number
        confessionBodyTxt.text = publicationData.description
        confessionDateTxt.text = publicationData.date.toString()
        confessionNameTxt.text = publicationData.title
    }
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter(CommentsProvider.commentsList)
    }
}