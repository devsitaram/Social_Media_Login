package com.edu.socialmediallogin.presentation.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.edu.socialmediallogin.domain.model.VideoModel

class VideoPlayViewModel: ViewModel() {

//    private var _videoDetails = mutableStateOf<VideoModel?>(null)
private var _videoDetails by mutableStateOf<VideoModel?>(null)

    var videoDetails: VideoModel? = _videoDetails

    fun addVideoDetails(newVideoDetails: VideoModel) {
        _videoDetails = newVideoDetails
    }
}