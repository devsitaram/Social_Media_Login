package com.edu.socialmediallogin.presentation.state

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess : String? = null,
    val isError: String? = ""
)