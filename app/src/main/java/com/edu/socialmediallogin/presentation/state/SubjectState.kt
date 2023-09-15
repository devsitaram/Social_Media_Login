package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.domain.model.SubjectModel

data class SubjectState  (
    val isLoading: Boolean = false,
    val data: List<SubjectModel>? = null,
    val error: String = ""
)