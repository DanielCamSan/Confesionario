package edu.bo.confesionario.publications

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class ScopedViewModel: ViewModel(), Scope by Scope.Impl() {
    init {
        initScope()
    }
    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
