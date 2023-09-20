package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo

data class SignInState(
    val isLoading: Boolean = false,
    val data: AuthPojo? = null,
    val isError: String? = ""
)