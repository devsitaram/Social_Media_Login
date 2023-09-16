package com.edu.socialmediallogin.di.appmodule

import com.edu.socialmediallogin.domain.repository.SubjectRepository
import com.edu.socialmediallogin.domain.use_case.GetSearchSubjectUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDetails {

//    @Provides
//    fun provideLoginViewModel(userRepository: UserRepository): LoginViewModel {
//        return LoginViewModel(userRepository)
//    }

    // subject's use case
    @Provides
    @Singleton
    fun provideGetSearchUseCase(subjectRepository: SubjectRepository): GetSearchSubjectUseCase {
        return GetSearchSubjectUseCase(subjectRepository)
    }
}