package com.edu.socialmediallogin.data.source.remote.pojo.user

data class UserPojo(
    val result: UserProfiles? = null,
    val success: Boolean? = null,
    val abp: Boolean? = null,
    val error: Any? = null,
    val targetUrl: Any? = null,
    val unAuthorizedRequest: Boolean? = null
)

data class UserProfiles(
    val pendingBalance: String? = null,
    val isSchoolChatroomEnabled: Boolean? = null,
    val role: String? = null,
    val isActive: Boolean? = null,
    val countryId: Int? = null,
    val schoolPhotoUrl: String? = null,
    val isPasswordEmpty: Boolean? = null,
    val photoUrl: String? = null,
    val createdAt: String? = null,
    val emailAddress: String? = null,
    val userMode: String? = null,
    val isB2C: Boolean? = null,
    val nickname: String? = null,
    val id: Int? = null,
    val schoolName: String? = null,
    val isEBookPrintFeatureEnabled: Boolean? = null,
    val isLite: Boolean? = null,
    val gradeId: Int? = null,
    val tutorCreditBalance: Any? = null,
    val heading: String? = null,
    val subjects: String? = null,
    val fullName: String? = null,
    val userId: Int? = null,
    val isOtpEnabled: Boolean? = null,
    val phoneNumber: String? = null,
    val grade: String? = null,
    val location: String? = null,
    val isEmailConfirmed: Boolean? = null
)