package com.edu.socialmediallogin.presentation.state

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess : String? = null,
    val isError: String? = ""
)