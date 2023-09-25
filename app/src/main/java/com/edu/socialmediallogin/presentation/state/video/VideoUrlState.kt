package com.edu.socialmediallogin.presentation.state.video

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoUrlResult

class VideoUrlState (
    val isLoading: Boolean = false,
    val isData: VideoUrlResult? = null,
    val isError: String = ""
)