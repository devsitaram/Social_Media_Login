package com.edu.socialmediallogin.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.domain.use_case.VideoUseCase
import kotlinx.coroutines.flow.launchIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.presentation.state.VideoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

//@HiltViewModel
class VideoViewModel constructor(private val videoUseCase: VideoUseCase) : ViewModel() {

    private var _videoList = mutableStateOf(VideoState())
    val videoList: State<VideoState> get() = _videoList

//    init {
//        getVideos()
//    }
//
//    private fun getVideos() {
//        videoUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _videoList.value = VideoState(isLoading = true)
//                }
//
//                is Resource.Success -> {
//                    _videoList.value = VideoState(isData = it.data)
//                }
//
//                is Resource.Error -> {
//                    _videoList.value = VideoState(isError = it.message.toString())
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//
//    fun insertVideo(listOfVideo: List<VideoEntity>) {
//        videoUseCase(listOfVideo).launchIn(viewModelScope)
//    }
}