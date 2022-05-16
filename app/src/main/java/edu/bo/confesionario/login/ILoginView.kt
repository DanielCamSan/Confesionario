package edu.bo.confesionario.login

import android.view.View

interface ILoginView {
    fun sendData(view: View)
    fun showMessage(message: String)
    fun gotoHome()

}