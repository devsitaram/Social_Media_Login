package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.local.UserDao
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(
    private val apiService: ApiService,
    private val userDao: UserDao
) : SubjectRepository {
    override suspend fun getSubjects(): List<SubjectItem> {
        try {
            val listOfSubject = userDao.getAllSubjects()
            // if local database is empty then call the server
            return listOfSubject.ifEmpty {
                apiService.getSearchSubject().result.map { it }
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
    ): Boolean? {
        return try {
            val listOfSubjects = listOf(
                SubjectEntity(
                    photoUrl = photoUrl,
                    name = name,
                    description = description,
                    isIvy = isIvy
                )
            )
            userDao.insertSubject(subject = listOfSubjects)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteSubject(subject: SubjectEntity) {
        try {
            return userDao.deleteSubject(subject)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}