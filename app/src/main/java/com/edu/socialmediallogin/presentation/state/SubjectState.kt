package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem

data class SubjectState(
    val isLoading: Boolean = false,
    val data: List<SubjectItem?>? = null,
    val error: String = ""
)