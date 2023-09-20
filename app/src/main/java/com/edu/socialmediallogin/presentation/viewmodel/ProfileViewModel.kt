package com.edu.socialmediallogin.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.use_case.GetUserProfileUseCase
import com.edu.socialmediallogin.presentation.state.ProfileState
import com.edu.socialmediallogin.presentation.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getUserProfileUseCase: GetUserProfileUseCase) :
    ViewModel() {

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> get() = _profileState

    init {
        getUserProfiles()
    }

    private fun getUserProfiles() {
        getUserProfileUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _profileState.value = ProfileState(isLoading = true)
                }

                is Resource.Success -> {
                    _profileState.value = ProfileState(data = result.data)
                }

                is Resource.Error -> {
                    _profileState.value = ProfileState(isError = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}