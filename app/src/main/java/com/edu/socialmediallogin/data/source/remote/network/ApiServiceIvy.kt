package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.common.Constants
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoStreamUrlPojo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiServiceIvy {
    @GET("api/v1/embed/{embedToken}?provider=IVY") //https://api-ivy.advancedpedagogy.com/api/v1/embed/ token
    suspend fun getVideoStreamingUrl(@Path("embedToken") embedToken: String?): VideoStreamUrlPojo?
}