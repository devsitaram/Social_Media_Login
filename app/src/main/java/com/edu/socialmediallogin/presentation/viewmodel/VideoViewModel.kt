package com.edu.socialmediallogin.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.domain.use_case.VideoUseCase
import kotlinx.coroutines.flow.launchIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.presentation.state.video.VideoListState
import com.edu.socialmediallogin.presentation.state.video.VideoUrlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val videoUseCase: VideoUseCase) :
    ViewModel() {

    private var _videoList = mutableStateOf(VideoListState())
    val videoList: State<VideoListState> get() = _videoList

    fun getVideos(subjectId: Int?) {
        videoUseCase(subjectId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _videoList.value = VideoListState(isLoading = true)
                }

                is Resource.Success -> {
                    _videoList.value = VideoListState(isData = it.data)
                }

                is Resource.Error -> {
                    _videoList.value = VideoListState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertVideo(listOfVideo: List<VideoEntity>) {
        videoUseCase(listOfVideo).launchIn(viewModelScope)
    }


    private var _videoUrl = mutableStateOf(VideoUrlState())
    val videoUrl: State<VideoUrlState> get() = _videoUrl
    fun getVideoUrl(videoId: String?) {
        videoUseCase(videoId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _videoUrl.value = VideoUrlState(isLoading = true)
                }

                is Resource.Success -> {
                    _videoUrl.value = VideoUrlState(isData = it.data)
                }

                is Resource.Error -> {
                    _videoUrl.value = VideoUrlState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}