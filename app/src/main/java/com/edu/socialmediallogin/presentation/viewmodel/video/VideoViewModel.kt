package com.edu.socialmediallogin.presentation.viewmodel.video

import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.domain.use_case.video.VideoUseCase
import kotlinx.coroutines.flow.launchIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.presentation.state.video.VideoEmbedState
import com.edu.socialmediallogin.presentation.state.video.VideoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val videoUseCase: VideoUseCase) :
    ViewModel() {

    private var _videoList = mutableStateOf(VideoListState())
    val videoList: State<VideoListState> get() = _videoList

    // get video list
    fun getVideoList(subjectId: Int?) {
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

    private var _videoEmbedToken = mutableStateOf(VideoEmbedState())
    val videoEmbedToken: State<VideoEmbedState> get() = _videoEmbedToken
    fun getVideoEmbedToken(videoId: String?) {
        videoUseCase(videoId).onEach {
            when (it) {
                is Resource.Loading -> {
                    _videoEmbedToken.value = VideoEmbedState(isLoading = true)
                }

                is Resource.Success -> {
                    _videoEmbedToken.value = VideoEmbedState(isData = it.data)
                }

                is Resource.Error -> {
                    _videoEmbedToken.value = VideoEmbedState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}