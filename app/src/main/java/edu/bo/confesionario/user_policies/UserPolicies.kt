package edu.bo.confesionario.user_policies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.Help
import edu.bo.confesionario.R
import edu.bo.confesionario.login.Login
import edu.bo.confesionario.publications.Publications


class UserPolicies : AppCompatActivity() {

    private val goBackBtn : Button
        get() =  findViewById(R.id.go_back_btn)


    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)

    private val acceptPoliciesBtn : Button
        get() =  findViewById(R.id.acceptPoliciesBtn)
    private lateinit var userPoliciesViewModel: UserPoliciesViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_policies)
        userPoliciesViewModel = UserPoliciesViewModel(UserPoliciesRepository())

        goBackBtn.setOnClickListener{
            userPoliciesViewModel.acceptRules(false);
        }

        toolBarLogoutBtn.setOnClickListener{
            userPoliciesViewModel.acceptRules(false);
        }

        acceptPoliciesBtn.setOnClickListener{
            userPoliciesViewModel.acceptRules(true);
        }
        userPoliciesViewModel.model.observe(this, Observer(::updateUi));

        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }
    }

    private fun updateUi(uiModel: UserPoliciesViewModel.UiModel?) {
        when (uiModel) {
            is UserPoliciesViewModel.UiModel.RulesAccepted -> validateAccept(uiModel.resp)
        }
    }

    private fun validateAccept(resp: Response<String>) {
        if ( resp.code == 0) {
            val intent = Intent(this, Publications::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
            Toast.makeText(this, resp.message, Toast.LENGTH_LONG).show()
        } else {
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