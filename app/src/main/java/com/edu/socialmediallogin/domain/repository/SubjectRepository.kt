package com.edu.socialmediallogin.domain.repository

import android.content.Context
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem

interface SubjectRepository {

    suspend fun getSubjects(): List<SubjectItem>

    suspend fun insertSubject(photoUrl: String?, name: String?, description: String?, isIvy: Boolean?)

//    suspend fun getSearchSubject(query: String): List<SubjectModel>
}