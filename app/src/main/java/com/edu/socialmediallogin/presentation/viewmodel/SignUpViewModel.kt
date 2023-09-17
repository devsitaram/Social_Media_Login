package com.edu.socialmediallogin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.RegistrationState
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.presentation.state.SignUpState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//class SingUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

class SignUpViewModel : ViewModel() {

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

    // State to track the registration status
    val registrationState = MutableStateFlow<RegistrationState>(RegistrationState.INITIAL)

    // Function to initiate registration
    fun registerUser(username: String, password: String, otherParams: Map<String, String>) {
        registrationState.value = RegistrationState.LOADING

        // Make the API call here
        // Use viewModelScope to launch a coroutine for network operations
//        viewModelScope.launch {
//            try {
//                // Perform the POST request to the registration endpoint
//                val response = ApiService.registerUser(username, password, otherParams)
//
//                if (response.isSuccessful) {
//                    registrationState.value = RegistrationState.SUCCESS
//                } else {
//                    registrationState.value = RegistrationState.ERROR
//                }
//            } catch (e: Exception) {
//                registrationState.value = RegistrationState.ERROR
//            }
//        }
    }
}