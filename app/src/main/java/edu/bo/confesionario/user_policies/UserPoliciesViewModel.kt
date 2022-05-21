package edu.bo.confesionario.user_policies;
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


public class UserPoliciesViewModel(val userPoliciesRepository: UserPoliciesRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        class Loading(): UiModel()
        class Login(val resp: Response<String>) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()

    fun acceptPolicies(choose:Boolean) {
        _model.value = UiModel.Loading()
        val runnable = Runnable {
            _model.value = UiModel.Login(userPoliciesRepository.acceptPolicies(choose))
        }

        Handler().postDelayed(runnable, 3000)

    }
}