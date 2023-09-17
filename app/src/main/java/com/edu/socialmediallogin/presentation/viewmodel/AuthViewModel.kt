package com.edu.socialmediallogin.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.source.local.User
import com.edu.socialmediallogin.domain.repository.UserRepository
import com.edu.socialmediallogin.presentation.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    // LiveData or State for managing the UI
class AuthViewModel: ViewModel(){

    private val _loginResult = MutableLiveData<AuthResult>()
    val loginResult: LiveData<AuthResult> = _loginResult

    sealed class AuthResult {
        data class Success(val user: User) : AuthResult()
        data class Error(val message: String) : AuthResult()
    }

    // Function to handle user login
    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Check if the user exists
//                val user = userRepository.getUserByEmailAndPassword(email, password)
//                if (user != null) {
//                    // User found, emit success state
//                    _loginResult.postValue(AuthResult.Success(user))
//                } else {
//                    // User not found, emit error state
//                    _loginResult.postValue(AuthResult.Error("Invalid credentials"))
//                }
            } catch (e: Exception) {
                // Handle exceptions, e.g., network issues
                _loginResult.postValue(AuthResult.Error("Network error"))
            }
        }
    }

    // Function to handle user registration
//    fun register(username: String, email: String, password: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
                // Check if the user already exists
//                val existingUser = userRepository.getUserByEmail(email)
//                if (existingUser == null) {
//                    // User does not exist, proceed with registration
//                    val newUser = User(name = username, email = email, password = password)
//                    userRepository.insertUser(newUser)
//                    // Registration success, emit success state
//                    // LiveData.postValue(AuthResult.Success(newUser))
//                } else {
//                    // User already exists, emit error state
//                    // LiveData.postValue(AuthResult.Error("User already registered"))
//                }
//            } catch (e: Exception) {
//                // Handle exceptions, e.g., network issues
//                // LiveData.postValue(AuthResult.Error("Network error"))
//            }
//        }
//    }
}