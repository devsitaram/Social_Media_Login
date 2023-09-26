package com.edu.socialmediallogin.domain.use_case.video

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoStreamUrlResult
import com.edu.socialmediallogin.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class VideoIvyUseCase(private val videoRepository: VideoRepository) {

    operator fun invoke(embedToken: String?): Flow<Resource<VideoStreamUrlResult?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.getVideoStreamingUrl(embedToken)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}