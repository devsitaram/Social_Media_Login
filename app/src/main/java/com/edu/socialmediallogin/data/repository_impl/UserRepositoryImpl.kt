package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import com.edu.socialmediallogin.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {

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
            // Handle exceptions here or rethrow with a more descriptive message
            throw Exception("Login failed: ${e.message}", e)
        }
    }

    // get user profiles
    override suspend fun getLoginUserProfile(): UserPojo {
        try {
            val response = apiService.getUserProfiles()
            return response ?: throw Exception("Response failed: ${response?.error}")
        } catch (e: Exception) {
            // Handle exceptions here or rethrow with a more descriptive message
            throw Exception("Response Error: ${e.message}", e)
        }
    }

//    override suspend fun getLoginUser(email: String, password: String,  rememberClient: Boolean): UserDetails? {
//        try {
//            return apiService.getLoginUser(email, password, rememberClient).result
//        } catch (e: Exception){
//            throw Exception(e)
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
}