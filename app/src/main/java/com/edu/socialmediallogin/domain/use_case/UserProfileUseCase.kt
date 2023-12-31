package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.UserEntity
import com.edu.socialmediallogin.data.source.remote.pojo.user.ProfileResult
import com.edu.socialmediallogin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UserProfileUseCase(private val userRepository: UserRepository) {

    operator fun invoke(): Flow<Resource<ProfileResult>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = userRepository.getUserProfile()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    operator fun invoke(userEntity: UserEntity) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = userRepository.insertUserProfile(userEntity)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}