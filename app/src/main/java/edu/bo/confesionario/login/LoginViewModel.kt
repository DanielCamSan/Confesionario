package edu.bo.confesionario.login

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.bo.confesionario.user_policies.UserPolicies


class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(): UiModel()
        class Login(val resp: Response<String>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()

    fun login(userName: String = "", password: String = "", context: Context) {
        _model.value = UiModel.Loading()
        val runnable = Runnable {
            _model.value = UiModel.Login( loginRepository.login(userName, password))
        }

        Handler().postDelayed(runnable, 3000)

    }
}