package com.edu.socialmediallogin.domain.model

data class UserModel(
    val email: String,
    val username: String,
    val password: String
)

data class SubjectModel(
    val imageUrl: String
)