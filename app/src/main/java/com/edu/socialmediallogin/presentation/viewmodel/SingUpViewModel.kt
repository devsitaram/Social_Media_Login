package com.edu.socialmediallogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.presentation.state.SignUpState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//class SingUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

class SingUpViewModel :ViewModel() {
    private val _signUpState = Channel<SignUpState>()

    val singUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
//        repository.registerUser(email, password).collect { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _signUpState.send(SignUpState(isSuccess = "Sign in Success"))
//                }
//
//                is Resource.Loading -> {
//                    _signUpState.send(SignUpState(isLoading = true))
//                }
//
//                is Resource.Error -> {
//                    _signUpState.send(SignUpState(isError = result.message))
//                }
//            }
//        }
    }
}