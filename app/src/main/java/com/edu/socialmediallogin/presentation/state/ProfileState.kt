package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.user.UserProfiles

data class ProfileState(
    val isLoading: Boolean = false,
    val isData: UserProfiles? = null,
    val isError: String = ""
)

//data class ProfileState(
//    val isLoading: Boolean = false,
//    val isData: UserPojo? = null,
//    val isError: String = ""
//)