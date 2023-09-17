package com.edu.socialmediallogin.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "users")
//data class UserEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
//    @ColumnInfo("email")
//    val email: String,
//    @ColumnInfo("username")
//    val username: String,
//    @ColumnInfo("password")
//    val password: String
//)

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val password: String
)