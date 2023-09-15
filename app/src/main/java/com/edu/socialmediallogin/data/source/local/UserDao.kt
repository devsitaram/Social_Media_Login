package com.edu.socialmediallogin.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.edu.socialmediallogin.domain.model.UserModel

@Dao
interface UserDao {

    // Insert a new user
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: List<UserModel>): Boolean

    // Get a user by ID
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): UserEntity?

    // Update an existing user
    @Update
    suspend fun updateUser(user: UserEntity)

    // Delete a user
    @Delete
    suspend fun deleteUser(user: UserEntity)

}