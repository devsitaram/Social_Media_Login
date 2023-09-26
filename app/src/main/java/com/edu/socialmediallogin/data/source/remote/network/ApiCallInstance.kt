package com.edu.socialmediallogin.data.source.remote.network

import android.content.Context
import com.edu.socialmediallogin.data.common.ClientInterceptors
import com.edu.socialmediallogin.data.common.Constants.API_BASE_URL_MST
import com.edu.socialmediallogin.data.common.Constants.API_VIDEO_BASE_IVY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiCallInstance {
    companion object {

        fun getSubjectRetrofitInstance(context: Context): Retrofit {
            // create the object of httpLoggingInterceptor
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            // create object of okHttpClient
            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(ClientInterceptors(context)) // Add the custom interceptor
                    .build() // addInterceptor() header
            // return the retrofit
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL_MST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // converter
                .build()
        }

        fun getIvyRetrofitInstance(context: Context): Retrofit {
            // create the object of httpLoggingInterceptor
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            // create object of okHttpClient
            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(ClientInterceptors(context)) // Add the custom interceptor
                    .addInterceptor(httpLoggingInterceptor).build()
            // return the retrofit
            return Retrofit.Builder()
                .baseUrl(API_VIDEO_BASE_IVY)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // converter
                .build()
        }
    }
}