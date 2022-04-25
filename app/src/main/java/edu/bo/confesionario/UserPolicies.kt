package edu.bo.confesionario

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class UserPolicies : AppCompatActivity() {

    private val goBackBtn : Button
        get() =  findViewById(R.id.go_back_btn)


    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)

    private val acceptPoliciesBtn : Button
        get() =  findViewById(R.id.acceptPoliciesBtn)

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_policies)


        goBackBtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

        toolBarLogoutBtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

        acceptPoliciesBtn.setOnClickListener{
            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }


        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
    }
}