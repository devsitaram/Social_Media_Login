package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.UserEntity

interface UserRepository {
    suspend fun loginUser(email: String, password: String): UserEntity?
    suspend fun registerUser(email: String, name: String, password: String): Boolean?
}