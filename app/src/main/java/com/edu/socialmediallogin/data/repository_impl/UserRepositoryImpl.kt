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
    override suspend fun getUserProfile(context: Context): UserProfiles? {
        try {
            val profiles = dao.getUserProfiles()
        return if (profiles.toString().isNotEmpty()) {
            profiles
        } else {
            apiService.getUserProfiles()?.result
        }
        // return apiService.getUserProfiles()?.result
        } catch (e: Exception) {
            throw Exception("Response Error: ${e.message}", e)
        }
    }

    override suspend fun insertUserProfile(userEntity: UserEntity){
        try {
            dao.insertUserProfile(userEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}

//    override suspend fun getUserProfile(): UserPojo {
//        try {
//            val response = apiService.getUserProfiles()
//            return response ?: throw Exception("Response failed: ${response?.error}")
//        } catch (e: Exception) {
//            throw Exception("Response Error: ${e.message}", e)
//        }
//    }

//    override suspend fun insertUser(user: User) {
//        userDao.insertUser(user)
//    }
//
//    override suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
//        return userDao.getUserByEmailAndPassword(email, password)
//    }
//
//    override suspend fun getUserByEmail(email: String): String? {
//        return userDao.getUserByEmail(email)
//    }

//    override suspend fun loginUser(email: String, password: String): UserEntity? {
//        return userDao.loginUser(email, password)
//    }
//
//    override suspend fun registerUser(email: String, name: String, password: String): Boolean {
////        val userList = mutableListOf<UserModel>()
////        userList.add(UserModel(email, name, password))
////        return userDao.insertUser(userList)
//        return true
//    }
//}