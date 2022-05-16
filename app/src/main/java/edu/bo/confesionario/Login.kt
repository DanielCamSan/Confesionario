package edu.bo.confesionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.bo.confesionario.publications.Publications
import java.lang.Exception


class Login : AppCompatActivity() {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    private lateinit var googleSignInClient: GoogleSignInClient
    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, UserPolicies::class.java)
            startActivity(intent)
        }
        updateUI(currentUser, true)
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Toast.makeText(this, "Espere por favor!" , Toast.LENGTH_SHORT).show()
                if(!account.email.toString().endsWith("ucb.edu.bo")){
                    Toast.makeText(this, account.email , Toast.LENGTH_SHORT).show()
                    throw IllegalArgumentException("Esta cuenta no pertenece a la universidad");

                }else{
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: Exception) {
                FirebaseAuth.getInstance().signOut()
                GoogleSignIn.getClient(
                    this,
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                ).signOut()
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.message.toString() , Toast.LENGTH_SHORT).show()
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    task.result.additionalUserInfo.toString();
                    val isNew = task.result.additionalUserInfo!!.isNewUser
                    val user = auth.currentUser
                    Toast.makeText(this, R.string.login_msg  , Toast.LENGTH_SHORT).show()

                    updateUI(user, isNew)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, R.string.failed_login_msg, Toast.LENGTH_SHORT).show()
                    updateUI(null,false)
                }
            }
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        //        val intent = Intent(applicationContext, UserPolicies::class.java)
        //        startActivity(intent)
    }
    // [END signin]

    private fun updateUI(user: FirebaseUser?, isNew: Boolean) {
        lateinit var intent: Intent;
        if(user!=null){
            if(isNew){
                intent = Intent(applicationContext, UserPolicies::class.java)

            }
            else{
                intent = Intent(applicationContext, Publications::class.java)
            }
            startActivity(intent)
        }
    }


    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    private val loginBtn : Button
        get() =  findViewById(R.id.login_btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener{
            signIn();

            //            val intent = Intent(this, UserPolicies::class.java)
            //            startActivity(intent)

        }
        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        // [END config_signin]


        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]


    }

}