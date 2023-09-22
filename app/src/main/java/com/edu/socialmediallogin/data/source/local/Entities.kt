package com.edu.socialmediallogin.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("pendingBalance")
    val pendingBalance: String? = null,
    @ColumnInfo("isSchoolChatroomEnabled")
    val isSchoolChatroomEnabled: Boolean? = null,
    @ColumnInfo("role")
    val role: String? = null,
    @ColumnInfo("isActive")
    val isActive: Boolean? = null,
    @ColumnInfo("countryId")
    val countryId: Int? = null,
    @ColumnInfo("schoolPhotoUrl")
    val schoolPhotoUrl: String? = null,
    @ColumnInfo("isPasswordEmpty")
    val isPasswordEmpty: Boolean? = null,
    @ColumnInfo("photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo("createdAt")
    val createdAt: String? = null,
    @ColumnInfo("emailAddress")
    val emailAddress: String? = null,
    @ColumnInfo("userMode")
    val userMode: String? = null,
    @ColumnInfo("isB2C")
    val isB2C: Boolean? = null,
    @ColumnInfo("nickname")
    val nickname: String? = null,
    @ColumnInfo("schoolName")
    val schoolName: String? = null,
    @ColumnInfo("isEBookPrintFeatureEnabled")
    val isEBookPrintFeatureEnabled: Boolean? = null,
    @ColumnInfo("isLite")
    val isLite: Boolean? = null,
    @ColumnInfo("gradeId")
    val gradeId: Int? = null,
    @ColumnInfo("tutorCreditBalance")
    val tutorCreditBalance: String? = null,
    @ColumnInfo("heading")
    val heading: String? = null,
    @ColumnInfo("subjects")
    val subjects: String? = null,
    @ColumnInfo("fullName")
    val fullName: String? = null,
    @ColumnInfo("userId")
    val userId: Int? = null,
    @ColumnInfo("isOtpEnabled")
    val isOtpEnabled: Boolean? = null,
    @ColumnInfo("phoneNumber")
    val phoneNumber: String? = null,
    @ColumnInfo("grade")
    val grade: String? = null,
    @ColumnInfo("location")
    val location: String? = null,
    @ColumnInfo("isEmailConfirmed")
    val isEmailConfirmed: Boolean? = null
)

@Entity(tableName = "subject")
data class SubjectEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name ="photoUrl")
    val photoUrl: String? = null,
    @ColumnInfo(name ="name")
    val name: String? = null,
    @ColumnInfo(name ="description")
    val description: String? = null,
    @ColumnInfo(name ="isIvy")
    val isIvy: Boolean? = null
)