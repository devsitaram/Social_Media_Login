package com.edu.socialmediallogin.domain.use_case

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.pojo.user.ProfileResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult
import com.edu.socialmediallogin.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class VideoUseCase(private val videoRepository: VideoRepository) {

    operator fun invoke(): Flow<Resource<VideoResult?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.getVideoDetails()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    operator fun invoke(listOfVideo : List<VideoEntity>) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.insertVideos(listOfVideo)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}