package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo

interface UserRepository {

    suspend fun getLoginUserAuth(email: String, password: String): AuthPojo?

    suspend fun getUserProfile(): UserPojo?

//    suspend fun loginUser(email: String, password: String): UserEntity?
//    suspend fun registerUser(email: String, name: String, password: String): Boolean?
//    suspend fun insertUser(user: User)
//    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
//    suspend fun getUserByEmail(email: String): String?

}