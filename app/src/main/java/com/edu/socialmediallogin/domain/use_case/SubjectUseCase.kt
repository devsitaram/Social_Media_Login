package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class SubjectUseCase(private val subjectRepository: SubjectRepository) {

    // get subject
    operator fun invoke(): Flow<Resource<List<SubjectResult?>?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.getSubjects()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    // insert subject in local database
    operator fun invoke(listOfSubject: List<SubjectEntity>) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.insertSubject(listOfSubject)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    // delete by id
    operator fun invoke(id: Int) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.deleteSubject(id)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}