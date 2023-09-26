package com.edu.socialmediallogin.presentation.state.video

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoStreamUrlResult

class VideoStreamUrlState(
    val isLoading: Boolean = false,
    val isData: VideoStreamUrlResult? = null,
    val isError: String = ""
)