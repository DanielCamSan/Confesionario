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
import edu.bo.confesionario.publications.MainViewModel
import edu.bo.data.PublicationsRepository
import edu.bo.framework.PublicationDataSource
import edu.bo.framework.RetrofitBuilder
import edu.bo.usecases.PostPublication


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
        get() =  findViewById(R.id.infoBtn)
    private val text_confesion : TextView
        get() =  findViewById(R.id.infoBtn)
    private val switch_anonymous: Switch
        get() =  findViewById(R.id.switch_anonymous)


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
        /*switch_anonymous.setOnCheckedChangeListener{
            switch_anonymous.thumbTintList=
        }*/
        btn_publish.setOnClickListener{
            val title= text_title.text.toString()
            val category= text_category.text.toString()
            val confess= text_confesion.text.toString()
            val anonym= switch_anonymous.isActivated.toString()



            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

    }

}