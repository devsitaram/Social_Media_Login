package com.edu.socialmediallogin.presentation.state.video

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult

class VideoListState(
    val isLoading: Boolean = false,
    val isData: VideoResult? = null,
    val isError: String = ""
)