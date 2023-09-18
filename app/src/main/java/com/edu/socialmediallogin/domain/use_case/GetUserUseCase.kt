package com.edu.socialmediallogin.domain.use_case

import android.util.Log
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserPojo
import com.edu.socialmediallogin.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(email: String, password: String, rememberClient: Boolean): Flow<Resource<UserPojo?>> = flow {
        emit(Resource.Loading())
        try {
            val result = emit(Resource.Success(data = userRepository.getLoginUser(email, password, rememberClient)))
            Log.e("result", "result: $result")
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}