package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetUserProfileUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Resource<UserPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = userRepository.getLoginUserProfile()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}