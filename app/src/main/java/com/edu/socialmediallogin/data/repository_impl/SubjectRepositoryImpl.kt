package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.local.Dao
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(private val apiService: ApiService, private val dao: Dao) : SubjectRepository {
    override suspend fun getSubjects(): List<SubjectResult>? {
        try {
            val listOfSubject = dao.getSubject()
            return listOfSubject?.ifEmpty {
                apiService.getSubjects().result?.map { it }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun insertSubject(listOfSubject: List<SubjectEntity>) {
        try {
            dao.insertSubject(listOfSubject = listOfSubject)
        } catch (e: Exception) {
            throw Exception("Error")
        }
    }

    override suspend fun deleteSubject(id: Int) {
        try {
            return dao.deleteSubject(id)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}