package com.edu.socialmediallogin.di.appmodule

import com.edu.socialmediallogin.domain.repository.SubjectRepository
import com.edu.socialmediallogin.domain.repository.UserRepository
import com.edu.socialmediallogin.domain.use_case.GetLoginAuthUseCase
import com.edu.socialmediallogin.domain.use_case.GetSubjectUseCase
import com.edu.socialmediallogin.domain.use_case.GetUserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDetails {

    // user's login use case
    @Provides
    @Singleton
    fun provideAuthUseCase(userRepository: UserRepository): GetLoginAuthUseCase {
        return GetLoginAuthUseCase(userRepository)
    }

    // user's profiles
    @Provides
    @Singleton
    fun provideProfileUseCase(userRepository: UserRepository): GetUserProfileUseCase {
        return GetUserProfileUseCase(userRepository)
    }
    // subject's use case
    @Provides
    @Singleton
    fun provideSubjectUseCase(subjectRepository: SubjectRepository): GetSubjectUseCase {
        return GetSubjectUseCase(subjectRepository)
    }
}