package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.common.Constants.SUBJECT_AUTH_TOKEN
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(private val apiService: ApiService) : SubjectRepository {

    override suspend fun getSearchSubject(): List<SubjectItem> {
        try {
            return apiService.getSearchSubject(SUBJECT_AUTH_TOKEN).result.map { it }
        } catch (e: Exception){
            throw Exception(e)
        }
    }
}