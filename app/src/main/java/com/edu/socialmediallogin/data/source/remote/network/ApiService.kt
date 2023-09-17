package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.source.remote.pojo.SubjectListDTO
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.RegistrationRequest
import com.google.android.gms.common.api.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "Accept-Encoding: gzip, deflate, br",
        "Accept-Language: en",
        "Authorization: Bearer\neyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjkwNjg2IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6Im5wMDFtYTRzMjIwMDAzQGlzbGluZ3RvbmNvbGxlZ2UuZWR1Lm5wIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIjoibnAwMW1hNHMyMjAwMDNAaXNsaW5ndG9uY29sbGVnZS5lZHUubnAiLCJBc3BOZXQuSWRlbnRpdHkuU2VjdXJpdHlTdGFtcCI6IkRXNUxJRFNLRUlKQVdMQUFFSFg1UlhTNTRMMlZIMlVSIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiU3R1ZGVudCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI5MDY4NiIsImp0aSI6IjgxZmE4MjM0LWU0NDctNDYxYy1hMjI5LWFhYjRhNWQwZjkzZCIsImlhdCI6MTY5NDk2ODkzMiwibmJmIjoxNjk0OTY4OTMyLCJleHAiOjE2OTYxNzg1MzIsImlzcyI6IkFQb2xsbyIsImF1ZCI6IkFQb2xsbyJ9.AV4nwJ_-IpOioyqq5h-Na4-jUkz0wB5gAjFCwfuhMpw"
    )
    @GET("students/profile/subjects/")
    suspend fun getSearchSubject(): SubjectPojo

//    @POST("api/student/sign-up-new")
//    suspend fun registerUser(@Body requestBody: RegistrationRequest) // : Response<RegistrationResponse>

//    @GET("api/")
//    suspend fun getSearchSubject(@Query("key") key: String, @Query("q") query: String): SubjectListDT

//    @GET("api/")
//    suspend fun getSearchSubject(@Query("key") key: String, @Query("q") query: String): SubjectListDTO
}