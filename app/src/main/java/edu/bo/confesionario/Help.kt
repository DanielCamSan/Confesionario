package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

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

    }
}