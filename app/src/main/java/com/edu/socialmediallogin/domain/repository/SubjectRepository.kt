package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem

interface SubjectRepository {

    suspend fun getSearchSubject(): List<SubjectItem>
//    suspend fun getSearchSubject(query: String): List<SubjectModel>
}