package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.common.toDomainModel
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.domain.model.SubjectModel
import com.edu.socialmediallogin.domain.repository.SubjectRepository
import kotlin.Exception

class SubjectRepositoryImpl(private val apiService: ApiService) : SubjectRepository {
    // https://pixabay.com/api/?key=39343490-929c50e58ac235a67f48917fa&q=sunflower
    override suspend fun getSearchSubject(query: String): List<SubjectModel> {
        try {
            return apiService.getSearchSubject(
                key = "39343490-929c50e58ac235a67f48917fa",
                query = query
            ).hits.map { it.toDomainModel() }
        } catch (e: Exception){
            throw Exception(e)
        }
    }
}