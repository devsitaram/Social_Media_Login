package com.edu.socialmediallogin.presentation.viewmodel.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.use_case.LoginAuthUseCase
import com.edu.socialmediallogin.presentation.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginAuthUseCase: LoginAuthUseCase) :
    ViewModel() {

    private val _signInState = mutableStateOf(SignInState())

    val signInState: State<SignInState> get() = _signInState

    fun getLoginUserAuth(email: String, password: String) {
        loginAuthUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _signInState.value = SignInState(isLoading = true)
                }

                is Resource.Success -> {
                    _signInState.value = SignInState(data = result.data)
                }

                is Resource.Error -> {
                    _signInState.value = SignInState(isError = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}