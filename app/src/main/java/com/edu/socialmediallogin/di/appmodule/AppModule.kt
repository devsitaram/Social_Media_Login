package com.edu.socialmediallogin.di.appmodule

import android.content.Context
import androidx.room.Room
import com.edu.socialmediallogin.data.common.Constants
import com.edu.socialmediallogin.data.common.Constants.API_BASE_URL
import com.edu.socialmediallogin.data.repository_impl.FirebaseAuthRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.SubjectRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.UserRepositoryImpl
import com.edu.socialmediallogin.data.source.local.RoomDatabaseHelper
import com.edu.socialmediallogin.data.source.local.Dao
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

    // get room database instance
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): Dao {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDatabaseHelper::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build().userDao()
    // multi table migrate fallbackToDestructiveMigration
    }

    // get api instance
    @Provides
    fun provideApiService(): ApiService {
        // create object of httpLoggingInterceptor
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        // create object of okHttpClient
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        // create an instance of the retrofit and return
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL) // Replace with your base URL
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    //user auth and profile
    @Provides
    fun provideUserRepo(apiService: ApiService, dao: Dao): UserRepository {
        return UserRepositoryImpl(apiService, dao)
    }

    // subject
    @Provides
    fun provideSubjectRepo(apiService: ApiService, dao: Dao): SubjectRepository {
        return SubjectRepositoryImpl(apiService, dao)
    }


    // firebase
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return FirebaseAuthRepositoryImpl(firebaseAuth)
    }
}