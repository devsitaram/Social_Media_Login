package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.RoomDao
import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceMst
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.ProfileResult
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import com.edu.socialmediallogin.domain.repository.UserRepository

class UserRepositoryImpl(private val apiServiceMst: ApiServiceMst, private val roomDao: RoomDao) :
    UserRepository {

    // login authentications
    override suspend fun getLoginUserAuth(email: String, password: String): AuthPojo {
        try {
            val loginRequestModel = LoginRequestModel(
                userNameOrEmailAddress = email,
                password = password,
                rememberClient = false,
                couponCode = "",
            )
            val response = apiServiceMst.getLoginUserAuth(loginRequestModel)
            return response ?: throw Exception("Login failed: ${response?.error}")
        } catch (e: Exception) {
            // Handle exceptions
            throw Exception("Login failed: ${e.message}", e)
        }
    }

    // get user profiles
    override suspend fun getUserProfile(): ProfileResult? {
        try {
            val profiles = roomDao.getUserProfiles()
            return if (profiles?.userId != null) {
                profiles
            } else {
                apiServiceMst.getUserProfiles()?.result
            }
        } catch (e: Exception) {
            throw Exception("Response Error: ${e.message}", e)
        }
    }

    override suspend fun insertUserProfile(userEntity: UserEntity) {
        try {
            roomDao.insertUserProfile(userEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}