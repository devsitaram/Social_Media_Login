package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.remote.pojo.user.AuthPojo
import com.edu.socialmediallogin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetLoginAuthUseCase(private val userRepository: UserRepository) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthPojo?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = userRepository.getLoginUserAuth(email, password)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}