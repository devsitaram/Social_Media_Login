package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo

data class SignInState(
    val isLoading: Boolean = false,
    val data: UserPojo? = null,
    val isError: String? = ""
)