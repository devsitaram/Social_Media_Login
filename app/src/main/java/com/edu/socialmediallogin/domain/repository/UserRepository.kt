package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.ProfileResult

interface UserRepository {

    suspend fun getLoginUserAuth(email: String, password: String): AuthPojo?

    suspend fun getUserProfile(): ProfileResult?

    suspend fun insertUserProfile(userEntity: UserEntity)
}