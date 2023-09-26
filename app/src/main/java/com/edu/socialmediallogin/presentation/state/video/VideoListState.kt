package com.edu.socialmediallogin.presentation.state.video

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListResult

class VideoListState(
    val isLoading: Boolean = false,
    val isData: VideoListResult? = null,
    val isError: String = ""
)