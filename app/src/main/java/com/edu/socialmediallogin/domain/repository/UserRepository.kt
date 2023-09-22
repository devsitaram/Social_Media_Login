package com.edu.socialmediallogin.domain.repository

import android.content.Context
import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserProfiles

interface UserRepository {

    suspend fun getLoginUserAuth(email: String, password: String): AuthPojo?

    suspend fun getUserProfile(): UserProfiles?

    suspend fun insertUserProfile(userEntity: UserEntity)

//    suspend fun loginUser(email: String, password: String): UserEntity?
//    suspend fun registerUser(email: String, name: String, password: String): Boolean?
//    suspend fun insertUser(user: User)
//    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
//    suspend fun getUserByEmail(email: String): String?

}