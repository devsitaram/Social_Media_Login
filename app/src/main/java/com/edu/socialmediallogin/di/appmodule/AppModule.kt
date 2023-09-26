package com.edu.socialmediallogin.di.appmodule

import android.content.Context
import com.edu.socialmediallogin.data.repository_impl.FirebaseAuthRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.SubjectRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.UserRepositoryImpl
import com.edu.socialmediallogin.data.repository_impl.VideoRepositoryImpl
import com.edu.socialmediallogin.data.source.local.DatabaseHelper.Companion.getDatabaseInstance
import com.edu.socialmediallogin.data.source.local.RoomDao
import com.edu.socialmediallogin.data.source.remote.network.ApiCallInstance.Companion.getIvyRetrofitInstance
import com.edu.socialmediallogin.data.source.remote.network.ApiCallInstance.Companion.getSubjectRetrofitInstance
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceIvy
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceMst
import com.edu.socialmediallogin.domain.repository.AuthRepository
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import com.edu.socialmediallogin.domain.repository.UserRepository
import com.edu.socialmediallogin.domain.repository.VideoRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // get room database instance
    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): RoomDao {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            DatabaseHelper::class.java,
//            Constants.DATABASE_NAME
//        ).fallbackToDestructiveMigration()
//            .build().userDao()
        // multi table migrate fallbackToDestructiveMigration
         return getDatabaseInstance(context).userDao()
    }

    // get api instance
    @Provides
    @Singleton
    fun provideApiRetrofitInstance(@ApplicationContext context: Context): ApiServiceMst {
        // get subject retrofit instance mst
        return getSubjectRetrofitInstance(context).create(ApiServiceMst::class.java)
    }

    @Provides
    @Singleton
    fun provideVideoApiRetrofitInstance(@ApplicationContext context: Context): ApiServiceIvy {
        // get video retrofit instance ivy
        return getIvyRetrofitInstance(context).create(ApiServiceIvy::class.java)
    }

    //user auth and profile
    @Provides
    @Singleton
    fun provideUserRepoImpl(apiServiceMst: ApiServiceMst, roomDao: RoomDao): UserRepository {
        return UserRepositoryImpl(apiServiceMst, roomDao)
    }

    // subject
    @Provides
    @Singleton
    fun provideSubjectRepoImpl(apiServiceMst: ApiServiceMst, roomDao: RoomDao): SubjectRepository {
        return SubjectRepositoryImpl(apiServiceMst, roomDao)
    }

    // video
    @Provides
    @Singleton
    fun provideVideoRepoImpl(apiServiceMst: ApiServiceMst, apiServiceIvy: ApiServiceIvy, roomDao: RoomDao): VideoRepository {
        return VideoRepositoryImpl(apiServiceMst, apiServiceIvy, roomDao)
    }


    // firebase
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return FirebaseAuthRepositoryImpl(firebaseAuth)
    }
}