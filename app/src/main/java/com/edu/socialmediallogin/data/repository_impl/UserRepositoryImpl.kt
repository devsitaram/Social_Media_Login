package com.edu.socialmediallogin.data.repository_impl

import android.content.Context
import android.widget.Toast
import com.edu.socialmediallogin.data.source.local.Dao
import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserProfiles
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import com.edu.socialmediallogin.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService, private val dao: Dao) :
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
            val response = apiService.getLoginUserAuth(loginRequestModel)
            return response ?: throw Exception("Login failed: ${response?.error}")
        } catch (e: Exception) {
            // Handle exceptions
            throw Exception("Login failed: ${e.message}", e)
        }
    }

    // get user profiles
    override suspend fun getUserProfile(): UserProfiles? {
        try {
            val profiles = dao.getUserProfiles()
            return if (profiles?.userId != null) {
                profiles
            } else {
                apiService.getUserProfiles()?.result
            }
        } catch (e: Exception) {
            throw Exception("Response Error: ${e.message}", e)
        }
    }

    override suspend fun insertUserProfile(userEntity: UserEntity) {
        try {
            dao.insertUserProfile(userEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}