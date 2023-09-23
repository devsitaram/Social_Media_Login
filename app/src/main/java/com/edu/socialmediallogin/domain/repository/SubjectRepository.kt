package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult

interface SubjectRepository {

    suspend fun getSubjects(): List<SubjectResult>?

    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)

    suspend fun deleteSubject(id: Int)
}