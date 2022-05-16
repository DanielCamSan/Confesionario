package edu.bo.confesionario.login

import android.content.Context
import edu.bo.confesionario.R

class LoginPresenter(val loginView: ILoginView, val context: Context) : ILoginPresenter {
    override fun login(userName: String, password: String) {
        if( userName == "") {
            loginView.gotoHome()
        } else {
            loginView.showMessage( context.getString( R.string.login_error_session ))
        }
    }
}