package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.local.RoomDao
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceMst
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(private val apiServiceMst: ApiServiceMst, private val roomDao: RoomDao) : SubjectRepository {
    override suspend fun getSubjects(): List<SubjectResult>? {
        try {
            val listOfSubject = roomDao.getSubject()
            return listOfSubject?.ifEmpty {
                apiServiceMst.getSubjects().result?.map { it }
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun insertSubject(listOfSubject: List<SubjectEntity>) {
        try {
            roomDao.insertSubject(listOfSubject = listOfSubject)
        } catch (e: Exception) {
            throw Exception("Error")
        }
    }

    override suspend fun deleteSubject(id: Int) {
        try {
            return roomDao.deleteSubject(id)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}