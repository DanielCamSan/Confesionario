package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    private val loginBtn : Button
        get() =  findViewById(R.id.login_btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener{
            val intent = Intent(this, UserPolicies::class.java)
            startActivity(intent)

        }
    }
}