package com.edu.socialmediallogin.ui.firebase.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.edu.socialmediallogin.ui.firebase.presentation.SignInResult
import com.edu.socialmediallogin.ui.firebase.presentation.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSuccess = result.data != null,
                isError = result.errorMessage
            )
        }
    }

    fun resultState(){
        _state.update { SignInState() }
    }
}