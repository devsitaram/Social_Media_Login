package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetSearchSubjectUseCase(private val subjectRepository: SubjectRepository) {

    operator fun invoke(): Flow<Resource<List<SubjectItem?>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = subjectRepository.getSearchSubject()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

//    operator fun invoke(query: String): Flow<Resource<List<SubjectModel>>> = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(data = subjectRepository.getSearchSubject(query = query)))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.message.toString()))
//        }
//    }


//    operator fun invoke(query: String): Flow<Resource<List<SubjectModel>>> = flow {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success(data = subjectRepository.getSearchSubject(query = query)))
//        } catch (e: Exception) {
//            emit(Resource.Error(message = e.message.toString()))
//        }
//    }
}