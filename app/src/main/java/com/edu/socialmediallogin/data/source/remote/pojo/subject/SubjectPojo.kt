package com.edu.socialmediallogin.data.source.remote.pojo.subject

data class SubjectPojo(
    val result: List<SubjectItem>,
    val success: Boolean,
    val abp: Boolean,
    val error: Any,
    val targetUrl: Any,
    val unAuthorizedRequest: Boolean
)

