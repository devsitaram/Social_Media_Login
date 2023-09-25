package com.edu.socialmediallogin.di.appmodule

import com.edu.socialmediallogin.domain.repository.SubjectRepository
import com.edu.socialmediallogin.domain.repository.UserRepository
import com.edu.socialmediallogin.domain.repository.VideoRepository
import com.edu.socialmediallogin.domain.use_case.LoginAuthUseCase
import com.edu.socialmediallogin.domain.use_case.SubjectUseCase
import com.edu.socialmediallogin.domain.use_case.UserProfileUseCase
import com.edu.socialmediallogin.domain.use_case.VideoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDetails {

    // user's login use case
    @Provides
    @Singleton
    fun provideAuthUseCase(userRepository: UserRepository): LoginAuthUseCase {
        return LoginAuthUseCase(userRepository)
    }

    // user's profiles
    @Provides
    @Singleton
    fun provideProfileUseCase(userRepository: UserRepository): UserProfileUseCase {
        return UserProfileUseCase(userRepository)
    }

    // subject's use case
    @Provides
    @Singleton
    fun provideSubjectUseCase(subjectRepository: SubjectRepository): SubjectUseCase {
        return SubjectUseCase(subjectRepository)
    }

    // video
    @Provides
    @Singleton
    fun provideVideoUseCase(videoRepository: VideoRepository): VideoUseCase {
        return VideoUseCase(videoRepository)
    }
}