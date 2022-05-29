package edu.bo.confesionario.user_policies

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserPoliciesViewModel (val userPoliciesRepository: UserPoliciesRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class RulesAccepted(val resp: Response<String>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()

    fun acceptRules(state:Boolean) {
        val runnable = Runnable {
            _model.value = UiModel.RulesAccepted(userPoliciesRepository.acceptRules(state))
        }
        Handler().postDelayed(runnable, 0)
    }
}