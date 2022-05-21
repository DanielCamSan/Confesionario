package edu.bo.confesionario

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.adapter.CommentAdapter
import edu.bo.confesionario.login.Login
import edu.bo.confesionario.comments.MainViewModel
import edu.bo.data.CommentsRepository
import edu.bo.domain.Comment
import edu.bo.domain.Publication
import edu.bo.framework.CommentDataSource
import edu.bo.usecases.GetComments
import edu.bo.usecases.PostComment
import java.text.SimpleDateFormat
import java.util.*

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
    private val commentsLabel: TextView
        get() = findViewById(R.id.comments)
    private val commentInput: EditText
        get() = findViewById(R.id.newCommentText)
    private val newCommentBtn: Button
        get() = findViewById(R.id.confessionPublishBtn)
    lateinit var mainViewModel: MainViewModel
    private lateinit var publicationData: Publication
    private lateinit var commentsAdapter: CommentAdapter
    private var commentsList: MutableList<Comment?> = mutableListOf<Comment?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_confession)
        backBtn.findViewById<Button>(R.id.backButton)
        backBtn.setOnClickListener {
            finish()
        }
        //toolbar
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
        // get Publication Data from intent bundle
        val bundle = intent.extras
        var idPublication = bundle!!.getString("id")?.toInt()
        var category = bundle!!.getString("category")
        val number = bundle!!.getString("number")
        val title = bundle!!.getString("title")
        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        date.time = sdf.parse(bundle!!.getString("date")) // all done
        //val date =  Calendar.parse(bundle!!.getString("date"))
        val description = bundle!!.getString("description")
        val userName = bundle!!.getString("userName")

        publicationData = Publication(category, idPublication, title, description, date, userName)

        fillTextViews()

        // retrieve comments
        commentsAdapter = CommentAdapter(commentsList)
        val commentsRepository = CommentsRepository(CommentDataSource(commentsList, idPublication))
        mainViewModel = MainViewModel(GetComments(commentsRepository), PostComment(commentsRepository))
        mainViewModel.model.observe(this, Observer(::updateUi))
        mainViewModel.loadComments()

        // buttons actions
        commentsLabel.setOnClickListener{
            commentsLabel.text = String.format("%d comentarios", commentsList.size)
            initRecyclerView()
        }
        newCommentBtn.setOnClickListener{
            val uid = UUID.randomUUID().toString()
            var username = FirebaseAuth.getInstance().currentUser?.displayName
            var userId = FirebaseAuth.getInstance().currentUser?.uid
            val newComment: Comment = Comment(uid,username, userId, idPublication?.toInt(), commentInput.text.toString(), Calendar.getInstance())
            mainViewModel.createComment(newComment)
            commentInput.text.clear()
        }
    }
    private fun fillTextViews(){
        var date = publicationData.date.get(Calendar.YEAR).toString()
        date = date + "-" + (if (publicationData.date.get(Calendar.MONTH)<10) "0" else "") + publicationData.date.get(Calendar.MONTH).toString()
        date = date + "-" + (if (publicationData.date.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + publicationData.date.get(Calendar.DAY_OF_MONTH).toString()
        userNameTxt.text = publicationData.userName
        confessionCategoryTxt.text = publicationData.category
        confessionNumberTxt.text = "Confesión #${publicationData.id.toString()}"
        confessionBodyTxt.text = publicationData.description
        confessionDateTxt.text = date.toString()
        confessionNameTxt.text = publicationData.title
    }
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = commentsAdapter
    }
    private fun updateCommentsList(comments: List<Comment?>){
        commentsAdapter.notifyDataSetChanged()
    }
    private fun updateUi(model: MainViewModel.UiModel?){
        when ( model) {
            is MainViewModel.UiModel.Content -> updateCommentsList(model.commentsList)
        }

    }
}