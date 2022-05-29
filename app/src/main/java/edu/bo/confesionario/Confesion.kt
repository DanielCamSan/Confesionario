package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.publications.Publications
import edu.bo.confesionario.publish.MainViewModel
import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication
import edu.bo.framework.DatabaseRef
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
    private val spinner : Spinner
        get() =  findViewById(R.id.text_category)
    private val text_confesion : TextView
        get() =  findViewById(R.id.text_confesion)
    private val switch_anonymous: Switch
        get() =  findViewById(R.id.switch_anonymous)

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confesion)
        val listOfSpinner=resources.getStringArray(R.array.planets_array)
        //val listOfSpinner= listOf("libros","fiestas","clases","confesiones")
        val adaptor = ArrayAdapter(this,android.R.layout.simple_spinner_item,listOfSpinner)
        spinner.adapter=adaptor
        var category= ""
        spinner.onItemSelectedListener=object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val category=listOfSpinner[p2]
                //println(listOfSpinner[p2])
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                category=""
            }

        }
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

            val description= text_confesion.text.toString()
            var anonymous=true
            switch_anonymous.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    anonymous=false
            }
            var userName=FirebaseAuth.getInstance().currentUser?.displayName
            if (anonymous) {
                userName="Anonimo"
            }
            val newPublication : Publication = Publication(category,"0" ,title, description,
                Calendar.getInstance(), userName,0)
            val publicationsRepository = PublicationsRepository(DatabaseRef())
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