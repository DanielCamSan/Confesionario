package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.publications.Publications


class Confesion : AppCompatActivity() {
    private val btn_back : Button
        get() = findViewById(R.id.btn_back)
    private val btn_publish : Button
        get() = findViewById(R.id.btn_publish)
    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn : ImageButton
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
            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

    }
}