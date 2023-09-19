package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // get user
    @POST("TokenAuth/Authenticate")
    suspend fun getLoginUser(@Body loginRequestModel: LoginRequestModel): UserPojo?

    // get subject
    @GET("students/profile/subjects/")
    suspend fun getSearchSubject(@Header("Authorization") authorization: String): SubjectPojo
}