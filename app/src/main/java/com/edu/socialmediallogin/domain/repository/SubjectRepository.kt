package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.domain.model.SubjectModel

interface SubjectRepository {

    suspend fun getSearchSubject(query: String): List<SubjectModel>
//    suspend fun getSearchSubject(query: String): List<SubjectModel>
}