package com.edu.socialmediallogin.data.repository_impl

import android.util.Log
import android.widget.Toast
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.domain.repository.UserRepository
import kotlin.coroutines.coroutineContext

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {

    override suspend fun getLoginUser(email: String, password: String, rememberClient: Boolean): UserPojo? {
        try {
            val response = apiService.getLoginUser(email, password)
            if (response != null){
                Log.e("response: ", "response: ${response.success}")
                return response
            } else {
                Log.e("response: ", "response: ${response?.error}")
                // Handle the error here, either by returning an error state or throwing an exception
                throw Exception("Login failed: ${response?.error}")
            }
        } catch (e: Exception) {
            // Handle exceptions here or rethrow with a more descriptive message
            throw Exception("Login failed: ${e.message}", e)
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