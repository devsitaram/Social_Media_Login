package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.Dao
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.network.ApiService
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoUrlResult
import com.edu.socialmediallogin.domain.repository.VideoRepository

class VideoRepositoryImpl(private val apiService: ApiService, private val dao: Dao) :
    VideoRepository {
    override suspend fun insertVideos(videoEntity: List<VideoEntity>) {
        try {
            dao.insertVideo(videoEntity)
        } catch (e: Exception) {
            throw Exception("video is not insert in local database $e")
        }
    }

    override suspend fun getVideoDetails(subjectId: Int?): VideoResult? {
        val videosResult = dao.getVideosById(subjectId)
        return if (videosResult?.id == null) {
            apiService.getVideos(subjectId).result
        } else {
            videosResult
        }
    }

    override suspend fun getVideoUrl(videoId: String?): VideoUrlResult? {
        return apiService.getVideoUrl(videoId)?.result
//        val videosResult = dao.getVideosById(videoId)
//        return if (videosResult?.id == null) {
//            apiService.getVideos(subjectId).result
//        } else {
//            videosResult
//        }
    }
}