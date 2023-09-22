package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.local.Dao
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(private val apiService: ApiService, private val dao: Dao) : SubjectRepository {
    override suspend fun getSubjects(): List<SubjectItem> {
        try {
            val listOfSubject = dao.getAllSubjects()
            // if local database is empty then call the server
            return listOfSubject.ifEmpty {
                apiService.getAllSubjects().result.map { it }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun insertSubject(
        photoUrl: String?,
        name: String?,
        description: String?,
        isIvy: Boolean?
    ) {
        try {
            val listOfSubjects = listOf(
                SubjectEntity(
                    photoUrl = photoUrl,
                    name = name,
                    description = description,
                    isIvy = isIvy
                )
            )
            dao.insertSubject(subject = listOfSubjects)
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