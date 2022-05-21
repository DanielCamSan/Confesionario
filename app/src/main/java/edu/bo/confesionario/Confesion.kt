package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Switch
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.publications.Publications
//import edu.bo.confesionario.publications.MainViewModel
import edu.bo.confesionario.publish.MainViewModel
import edu.bo.data.CommentsRepository
import edu.bo.data.PublicationsRepository
import edu.bo.framework.CommentDataSource
import edu.bo.domain.Publication
import edu.bo.framework.PublicationDataSource
import edu.bo.framework.RetrofitBuilder
import edu.bo.usecases.PostPublication
import java.util.*


class Confesion : AppCompatActivity() {
    private val btn_back : Button
        get() = findViewById(R.id.btn_back)
    private val btn_publish : Button
        get() = findViewById(R.id.btn_publish)
    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)
    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)
    private val text_title : TextView
        get() =  findViewById(R.id.text_title)
    private val text_category : TextView
        get() =  findViewById(R.id.text_category)
    private val text_confesion : TextView
        get() =  findViewById(R.id.text_confesion)
    private val switch_anonymous: Switch
        get() =  findViewById(R.id.switch_anonymous)

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confesion)
        btn_back.setOnClickListener{
            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }
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

        btn_publish.setOnClickListener{
            val uid = (0 until 10000000000000).random()
            val title= text_title.text.toString()
            val category= text_category.text.toString()
            val description= text_confesion.text.toString()
            var anonymous=true
            switch_anonymous.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    anonymous=false
            }
            var userName=FirebaseAuth.getInstance().currentUser?.displayName
            var userId = FirebaseAuth.getInstance().currentUser?.uid
            if (anonymous) {
                userName="Anonimo"
            }
            val newPublication : Publication =Publication(category, userId ,title, description,
                Calendar.getInstance(), userName, uid.toInt()
            )
            val publicationsRepository = PublicationsRepository(PublicationDataSource(RetrofitBuilder))
            mainViewModel= MainViewModel(PostPublication(publicationsRepository))
            //val publicationRepository = PublicationsRepository(PublicationDataSource(commentsList, idPublication))
            println(newPublication)
            mainViewModel.postPublication(newPublication)


            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

    }

}