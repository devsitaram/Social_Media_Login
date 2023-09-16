package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.source.remote.pojo.SubjectListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getSearchSubject(@Query("key") key: String, @Query("q") query: String): SubjectListDTO


//    @GET("api/")
//    suspend fun getSearchSubject(@Query("key") key: String, @Query("q") query: String): SubjectListDT
}