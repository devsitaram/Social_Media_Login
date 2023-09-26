package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.common.Constants.AUTHORIZATION
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoEmbedTokenPojo
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListPojo
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceMst {

    // get user
    @POST("TokenAuth/Authenticate/")
    suspend fun getLoginUserAuth(@Body loginRequestModel: LoginRequestModel): AuthPojo?

    @GET("students/profile")
    suspend fun getUserProfiles(): UserPojo?

    // get subject
    @GET("students-subject-library")
    suspend fun getSubjects(): SubjectPojo

    // get video
//    @Headers(AUTHORIZATION)
    @GET("subjects/{subjectId}/average-tree")
    suspend fun getVideoList(@Path("subjectId") subjectId: Int?): VideoListPojo?

//    @Headers(AUTHORIZATION)
    @GET("videos/{videoId}/embed") //62454185952b36001286d8d7
    suspend fun getVideoEmbedToken(@Path("videoId") videoId: String?): VideoEmbedTokenPojo?

}