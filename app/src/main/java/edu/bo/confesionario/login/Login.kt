package edu.bo.confesionario.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import edu.bo.confesionario.R
import edu.bo.confesionario.user_policies.UserPolicies

class Login : AppCompatActivity() , ILoginView {

    lateinit var loginPresenter: ILoginPresenter
    lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.btn_login)
        loginPresenter = LoginPresenter(this, applicationContext)
    }

    override fun sendData(view: View) {
        loginPresenter.login("" ,"")
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun gotoHome() {
        startActivity(Intent(this, UserPolicies::class.java))
    }
}