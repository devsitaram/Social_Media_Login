package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem

interface SubjectRepository {

    suspend fun getSubjects(): List<SubjectItem?>?

    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)

//    suspend fun insertSubject(photoUrl: String?, name: String?, description: String?, isIvy: Boolean?)

    suspend fun deleteSubject(id: Int)
}