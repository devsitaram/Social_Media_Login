package com.edu.socialmediallogin.presentation.state

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult

class VideoState(
    val isLoading: Boolean = false,
    val isData: VideoResult? = null,
    val isError: String = ""
)