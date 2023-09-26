package com.edu.socialmediallogin.presentation.viewmodel.video

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.domain.use_case.video.VideoIvyUseCase
import com.edu.socialmediallogin.presentation.state.video.VideoStreamUrlState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoIvyViewModel @Inject constructor(private val videoIvyUseCase: VideoIvyUseCase): ViewModel() {

    private var _videoUrl = mutableStateOf(VideoStreamUrlState())
    val videoStreamingUrl: State<VideoStreamUrlState> get() = _videoUrl
    fun getStreamingUrl(embedToken: String?) {
        videoIvyUseCase(embedToken).onEach {
            when (it) {
                is Resource.Loading -> {
                    _videoUrl.value = VideoStreamUrlState(isLoading = true)
                }

                is Resource.Success -> {
                    _videoUrl.value = VideoStreamUrlState(isData = it.data)
                }

                is Resource.Error -> {
                    _videoUrl.value = VideoStreamUrlState(isError = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}