package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult


data class SubjectState(
    val isLoading: Boolean = false,
    var isData: List<SubjectResult?>? = null,
    val isError: String = ""
)