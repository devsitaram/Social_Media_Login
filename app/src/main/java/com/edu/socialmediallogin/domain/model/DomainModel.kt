package com.edu.socialmediallogin.domain.model

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Query

data class GoogleUserModel(
    val name: String? = null,
    val email: String? = null,
)

data class SubjectModel(
    val imageUrl: String,
    val title: String,
    val urlDescriptions: String
)

// video
@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class VideoModel(
    val title: String,
    val descriptions: String,
    val videoUri: String,
) : Parcelable

// user login request
data class LoginRequestModel(
    val userNameOrEmailAddress: String,
    val password: String,
    val rememberClient: Boolean,
    val couponCode: String,
)

data class LoginResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val refreshToken: String,
    val userId: String,
)