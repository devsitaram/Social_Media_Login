package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetSubjectUseCase(private val subjectRepository: SubjectRepository) {

    // get subject
    operator fun invoke(): Flow<Resource<List<SubjectItem?>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.getSubjects()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    // insert subject in local database
    operator fun invoke(photoUrl: String?, name: String?, description: String?, isIvy: Boolean?) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.insertSubject(photoUrl, name, description, isIvy)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    operator fun invoke(subject: SubjectEntity) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.deleteSubject(subject)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}