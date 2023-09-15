package com.edu.socialmediallogin.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class UserModel(
    val email: String,
    val username: String,
    val password: String
)

data class SubjectModel(
    val imageUrl: String,
    val title: String,
    val urlDescriptions: String
)

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class VideoModel(
    val title: String,
    val descriptions: String,
    val videoUri: String,
) : Parcelable