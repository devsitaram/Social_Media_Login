package com.edu.socialmediallogin.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("userId")
    val userId: Int = 0,
    @ColumnInfo("fullName")
    val fullName: String? = null,
    @ColumnInfo("emailAddress")
    val emailAddress: String? = null,
    @ColumnInfo("userPassword")
    val userPassword: String? = null,
    @ColumnInfo("phoneNumber")
    val phoneNumber: String? = null,
    @ColumnInfo("grade")
    val grade: String? = null,
    @ColumnInfo("location")
    val location: String? = null,
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