package com.edu.socialmediallogin.domain.repository

import android.content.Context
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import javax.security.auth.Subject

interface SubjectRepository {

    suspend fun getSubjects(): List<SubjectItem>

    suspend fun insertSubject(photoUrl: String?, name: String?, description: String?, isIvy: Boolean?)

    suspend fun deleteSubject(id: Int)
}