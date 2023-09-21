package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo

data class ProfileState(
    val isLoading: Boolean = false,
    val isData: UserPojo? = null,
    val isError: String = ""
)
