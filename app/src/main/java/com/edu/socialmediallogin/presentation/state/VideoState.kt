package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectItem
import com.edu.socialmediallogin.domain.model.VideoModel

class VideoState(
    val isLoading: Boolean = false,
    val data: List<VideoModel?>? = null,
    val error: String = ""
)