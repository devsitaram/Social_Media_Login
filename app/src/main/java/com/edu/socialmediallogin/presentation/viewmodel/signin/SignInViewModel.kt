package com.edu.socialmediallogin.presentation.viewmodel.signin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.repository.AuthRepository
import com.edu.socialmediallogin.domain.use_case.GetUserUseCase
import com.edu.socialmediallogin.presentation.state.SignInState
import com.edu.socialmediallogin.presentation.state.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val userUseCase: GetUserUseCase) : ViewModel() {

    private val _signInState = mutableStateOf(SignInState())
    val signInState: State<SignInState> get() = _signInState

    fun getLoginUser(email: String, password: String) {
        userUseCase(email, password).onEach { result ->
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