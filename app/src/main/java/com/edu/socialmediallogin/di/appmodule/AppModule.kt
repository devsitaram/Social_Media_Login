package com.edu.socialmediallogin.di.appmodule

import android.content.Context
import com.edu.socialmediallogin.data.common.Constants.API_BASE_URL
import com.edu.socialmediallogin.data.repository_impl.AuthRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.SubjectRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.UserRepositoryImpl
import com.edu.socialmediallogin.data.source.local.RoomDB
import com.edu.socialmediallogin.data.source.local.RoomDB.Companion.getDatabaseInstance
import com.edu.socialmediallogin.data.source.local.UserDao
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.domain.repository.AuthRepository
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import com.edu.socialmediallogin.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    // get room database instance
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): RoomDB {
        return getDatabaseInstance(context)
    }

    @Provides
    fun provideUserDao(database: RoomDB): UserDao {
        return database.userDao()
    }

    // get api instance
    @Provides
    fun provideApiService(): ApiService {
        // create object of httpLoggingInterceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // create object of okHttpClient
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        // create an object of the retrofit
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL) // Replace with your base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    // subject
    @Provides
    fun provideSubjectRepo(apiService: ApiService): SubjectRepository {
        return SubjectRepositoryImpl(apiService)
    }

    // user
    @Provides
    fun provideUserRepo(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}