package com.edu.socialmediallogin.presentation.state.video

import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoEmbedTokenResult

class VideoEmbedState (
    val isLoading: Boolean = false,
    val isData: VideoEmbedTokenResult? = null,
    val isError: String = ""
)