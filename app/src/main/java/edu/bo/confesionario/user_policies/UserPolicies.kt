package edu.bo.confesionario.user_policies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.Help
import edu.bo.confesionario.Publications
import edu.bo.confesionario.R
import edu.bo.confesionario.login.Login


class UserPolicies : AppCompatActivity() {
    private lateinit var userPoliciesViewModel: UserPoliciesViewModel
    lateinit var progressBar: ProgressBar

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
        progressBar = findViewById(R.id.progressBar)
        userPoliciesViewModel = UserPoliciesViewModel(UserPoliciesRepository())
        userPoliciesViewModel.model.observe(this, Observer(::updateUi))

        goBackBtn.setOnClickListener{
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

        acceptPoliciesBtn.setOnClickListener{
//            val intent = Intent(this, Publications::class.java)
//            startActivity(intent)
//            this.overridePendingTransition(0, 0);
            userPoliciesViewModel.acceptPolicies(true);

        }


        appInfoBtn.setOnClickListener{

            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
    }

    private fun updateUi(uiModel: UserPoliciesViewModel.UiModel?) {
        progressBar.visibility = if ( uiModel is UserPoliciesViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when (uiModel) {
            is UserPoliciesViewModel.UiModel.Login -> validateLogin(uiModel.resp)
        }
//        if ( uiModel == LoginViewModel.UiModel.Loading()) {
//            //mostrar Loading
//        } else if( uiModel == LoginViewModel.UiModel.Login()) {
//
//        }
    }

    private fun validateLogin(resp: Response<String>) {
        val intent = Intent(this, Publications::class.java)

        if ( resp.code == 0) {
            startActivity(intent)
            this.overridePendingTransition(0, 0);
            Toast.makeText(this, "Politicas de uso aceptadas!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Lo lamento algo raro sucedio", Toast.LENGTH_LONG).show()
        }
    }
}