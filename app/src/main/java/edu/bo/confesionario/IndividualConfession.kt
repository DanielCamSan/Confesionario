package edu.bo.confesionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.adapter.CommentAdapter
import java.text.SimpleDateFormat
import java.util.*
import edu.bo.domain.Publication

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
            FirebaseAuth.getInstance().signOut()
            GoogleSignIn.getClient(
                this,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            ).signOut()
            Toast.makeText(this, R.string.logout_msg, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, Login::class.java)
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
        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        date.time = sdf.parse(bundle!!.getString("date")) // all done
        //val date =  Calendar.parse(bundle!!.getString("date"))
        val description = bundle!!.getString("description")
        val userName = bundle!!.getString("userName")

        publicationData = Publication(category, number, title, description, date, userName)

        fillTextViews()
    }
    private fun fillTextViews(){
        var date = publicationData.date.get(Calendar.YEAR).toString()
        date = date + "-" + (if (publicationData.date.get(Calendar.MONTH)<10) "0" else "") + publicationData.date.get(Calendar.MONTH).toString()
        date = date + "-" + (if (publicationData.date.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + publicationData.date.get(Calendar.DAY_OF_MONTH).toString()
        userNameTxt.text = publicationData.userName
        confessionCategoryTxt.text = publicationData.category
        confessionNumberTxt.text = "Confesi??n #${publicationData.number}"
        confessionBodyTxt.text = publicationData.description
        confessionDateTxt.text = date.toString()
        confessionNameTxt.text = publicationData.title
    }
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CommentAdapter(CommentsProvider.commentsList)
    }
}