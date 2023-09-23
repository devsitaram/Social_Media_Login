package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.Dao
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult
import com.edu.socialmediallogin.domain.repository.VideoRepository

class VideoRepositoryImpl(private val apiService: ApiService, private val dao: Dao) :
    VideoRepository {
    override suspend fun insertVideos(videoEntity: List<VideoEntity>) {
        try {
            dao.insertVideo(videoEntity)
        } catch (e: Exception){
            throw Exception("Video is not insert $e")
        }
    }

    override suspend fun getVideoDetails(): VideoResult? {
        val videosResult = dao.getVideos()
        return if (videosResult?.id != null) {
            videosResult
        } else {
            apiService.getVideos().result
        }
    }
}