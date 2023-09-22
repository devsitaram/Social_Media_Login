package com.edu.socialmediallogin.data.source.remote.network

import com.edu.socialmediallogin.data.common.Constants.AUTHORIZATION
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserProfiles
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoPojo
import com.edu.socialmediallogin.domain.model.LoginRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    // get user
    @POST("TokenAuth/Authenticate/")
    suspend fun getLoginUserAuth(@Body loginRequestModel: LoginRequestModel): AuthPojo?

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjkwNjg2IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6Im5wMDFtYTRzMjIwMDAzQGlzbGluZ3RvbmNvbGxlZ2UuZWR1Lm5wIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIjoibnAwMW1hNHMyMjAwMDNAaXNsaW5ndG9uY29sbGVnZS5lZHUubnAiLCJBc3BOZXQuSWRlbnRpdHkuU2VjdXJpdHlTdGFtcCI6IkRXNUxJRFNLRUlKQVdMQUFFSFg1UlhTNTRMMlZIMlVSIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiU3R1ZGVudCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI5MDY4NiIsImp0aSI6IjA0NDMxNmJkLTUxY2YtNDY2ZS04ZmQ2LTA2Y2I0YjMzMTk5OSIsImlhdCI6MTY5NTE4NzI2OSwibmJmIjoxNjk1MTg3MjY5LCJleHAiOjE2OTYzOTY4NjksImlzcyI6IkFQb2xsbyIsImF1ZCI6IkFQb2xsbyJ9.A4qThXLnQ39cl4PVXcArLkU71X0a8koSI8mlcwKN0zM")
    @GET("students/profile")
    suspend fun getUserProfiles(): UserPojo?

    // get subject
    @Headers(AUTHORIZATION)
    @GET("students-subject-library")
    suspend fun getAllSubjects(): SubjectPojo

    // get video
    @Headers(AUTHORIZATION)
    @GET("subjects/157/average-tree")
    suspend fun getVideos(): VideoPojo

//    // get user
//    @POST("TokenAuth/Authenticate/")
//    suspend fun getLoginUserAuth(@Body loginRequestModel: LoginRequestModel): AuthPojo?
//
//    // student profile
//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjkwNjg2IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6Im5wMDFtYTRzMjIwMDAzQGlzbGluZ3RvbmNvbGxlZ2UuZWR1Lm5wIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIjoibnAwMW1hNHMyMjAwMDNAaXNsaW5ndG9uY29sbGVnZS5lZHUubnAiLCJBc3BOZXQuSWRlbnRpdHkuU2VjdXJpdHlTdGFtcCI6IkRXNUxJRFNLRUlKQVdMQUFFSFg1UlhTNTRMMlZIMlVSIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiU3R1ZGVudCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI5MDY4NiIsImp0aSI6IjA0NDMxNmJkLTUxY2YtNDY2ZS04ZmQ2LTA2Y2I0YjMzMTk5OSIsImlhdCI6MTY5NTE4NzI2OSwibmJmIjoxNjk1MTg3MjY5LCJleHAiOjE2OTYzOTY4NjksImlzcyI6IkFQb2xsbyIsImF1ZCI6IkFQb2xsbyJ9.A4qThXLnQ39cl4PVXcArLkU71X0a8koSI8mlcwKN0zM")
//    @GET("students/profile")
//    suspend fun getUserProfiles(): UserPojo?
//    // subject
//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjkwNjg2IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6Im5wMDFtYTRzMjIwMDAzQGlzbGluZ3RvbmNvbGxlZ2UuZWR1Lm5wIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIjoibnAwMW1hNHMyMjAwMDNAaXNsaW5ndG9uY29sbGVnZS5lZHUubnAiLCJBc3BOZXQuSWRlbnRpdHkuU2VjdXJpdHlTdGFtcCI6IkRXNUxJRFNLRUlKQVdMQUFFSFg1UlhTNTRMMlZIMlVSIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiU3R1ZGVudCIsImh0dHA6Ly93d3cuYXNwbmV0Ym9pbGVycGxhdGUuY29tL2lkZW50aXR5L2NsYWltcy90ZW5hbnRJZCI6IjEiLCJzdWIiOiI5MDY4NiIsImp0aSI6ImEwNTUyYzdhLTgxZTQtNGM1NS05ZDQ0LWYwNmQ1ZDk0NTc3YSIsImlhdCI6MTY5NTM2OTE1NywibmJmIjoxNjk1MzY5MTU3LCJleHAiOjE2OTY1Nzg3NTcsImlzcyI6IkFQb2xsbyIsImF1ZCI6IkFQb2xsbyJ9.Y8ED864Kvd_7qgzye-RRLT_DgK1N_vvpKAy76k0b4tw")
//    @GET("students/profile/subjects/")
//    suspend fun getAllSubjects(): SubjectPojo

}