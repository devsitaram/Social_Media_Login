package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.domain.model.VideoModel

class VideoState(
    val isLoading: Boolean = false,
    val isData: List<VideoModel?>? = null,
    val isError: String = ""
)