package com.edu.socialmediallogin.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert // insert and update both
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE emailAddress = :email AND userPassword = :password")
    suspend fun getUserByEmail(email: String?, password: String): List<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)

//    @Query("SELECT * FROM user ORDER BY id ASC")
//    fun getUserById(id: Long): Flow<List<User>>
//
//    @Query("SELECT * FROM user ORDER BY name ASC")
//    fun getUserByName(name: String): Flow<List<User>>
//
//    @Query("SELECT * FROM user ORDER BY email ASC")
//    fun getUserByEmail(email: String): Flow<List<User>>


//    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
//    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
//
//    @Query("SELECT * FROM user WHERE email = :email")
//    suspend fun getUserByEmail(email: String): String?

    // Insert a new user
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: List<UserModel>): Boolean

    // Get a user by ID
//    @Query("SELECT * FROM users WHERE id = :userId")
//    suspend fun getUserById(userId: Long): UserEntity?
//
//    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
//    suspend fun loginUser(email: String, password: String): UserEntity?
//
//    // Update an existing user
//    @Update
//    suspend fun updateUser(user: UserEntity)
//
//    // Delete a user
//    @Delete
//    suspend fun deleteUser(user: UserEntity)

}