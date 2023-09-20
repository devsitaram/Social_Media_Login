package com.edu.socialmediallogin.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val fullName: String,
    @ColumnInfo("emailAddress")
    val emailAddress: String? = null,
    val userPassword: String,
    val phoneNumber: String,
    val grade: String,
    val location: String,
)

@Entity(tableName = "subject")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name ="photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo(name ="name")
    val name: String? = null,
    @ColumnInfo(name ="description")
    val description: String? = null,
    @ColumnInfo(name ="isIvy")
    val isIvy: Boolean? = null
)