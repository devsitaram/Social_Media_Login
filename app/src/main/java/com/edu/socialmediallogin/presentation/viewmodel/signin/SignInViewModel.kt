package com.edu.socialmediallogin.presentation.viewmodel.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.repository.AuthRepository
import com.edu.socialmediallogin.presentation.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

//class SignInViewModel : ViewModel() {
    private val _signInState = Channel<SignInState>()

    val singInState = _signInState.receiveAsFlow()

//    fun loginUser(email: String, password: String) = viewModelScope.launch {
//        repository.loginUser(email, password).collect { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _signInState.send(SingInState(isSuccess = "Sign in Success"))
//                }
//
//                is Resource.Loading -> {
//                    _signInState.send(SingInState(isLoading = true))
//                }
//
//                is Resource.Error -> {
//                    _signInState.send(SingInState(isError = result.message))
//                }
//            }
//        }
//    }
}