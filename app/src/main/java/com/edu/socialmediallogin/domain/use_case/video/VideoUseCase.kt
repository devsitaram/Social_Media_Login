package com.edu.socialmediallogin.domain.use_case.video

import com.edu.socialmediallogin.data.common.Resource
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoEmbedTokenResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListResult
import com.edu.socialmediallogin.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class VideoUseCase(private val videoRepository: VideoRepository) {


    // insert in to local server
    operator fun invoke(listOfVideo: List<VideoEntity>) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.insertVideos(listOfVideo)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    // get list of video
    operator fun invoke(subjectId: Int?): Flow<Resource<VideoListResult?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.getVideoList(subjectId)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    // return the video embed token
    operator fun invoke(videoId: String?): Flow<Resource<VideoEmbedTokenResult?>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = videoRepository.getEmbedToken(videoId)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}