package com.edu.socialmediallogin.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.use_case.GetUserProfileUseCase
import com.edu.socialmediallogin.presentation.state.ProfileState
import com.edu.socialmediallogin.presentation.state.SignInState
import com.edu.socialmediallogin.presentation.state.SubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userProfileUseCase: GetUserProfileUseCase) :
    ViewModel() {

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> get() = _profileState

    init {
        getUserProfiles()
    }

    private fun getUserProfiles() {
        userProfileUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _profileState.value = ProfileState(isLoading = true)
                }

                is Resource.Success -> {
                    _profileState.value = ProfileState(isData = result.data)
                }

                is Resource.Error -> {
                    _profileState.value = ProfileState(isError = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertUser(
        fullName: String?,
        emailAddress: String?,
        userPassword: String?,
        phoneNumber: String?,
        grade: String?,
        location: String?
    ) {
        userProfileUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _subjectList.value = SubjectState(isLoading = true)
//                }
//
//                is Resource.Success -> {
//                    _subjectList.value = SubjectState(isSuccess = true)
//                }
//
//                is Resource.Error -> {
//                    _subjectList.value = SubjectState(isError = it.message.toString())
//                }
//            }
        }
    }
}