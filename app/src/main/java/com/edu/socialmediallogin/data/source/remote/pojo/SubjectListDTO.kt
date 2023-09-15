package com.edu.socialmediallogin.data.source.remote.pojo


data class SubjectListDTO(
    val hits: List<SubjectDTO>,
    val total: Int,
    val totalHits: Int
)