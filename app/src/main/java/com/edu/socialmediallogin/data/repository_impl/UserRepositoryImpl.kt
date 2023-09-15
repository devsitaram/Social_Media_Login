package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.UserDao
import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.domain.model.UserModel
import com.edu.socialmediallogin.domain.repository.UserRepository

private fun <E> MutableList<E>.add(index: E, element: Unit) {

}

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override suspend fun loginUser(email: String, password: String): UserEntity? {
        return userDao.loginUser(email, password)
    }

    override suspend fun registerUser(email: String, name: String, password: String): Boolean {
//        val userList = mutableListOf<UserModel>()
//        userList.add(UserModel(email, name, password))
//        return userDao.insertUser(userList)
        return true
    }
}