package com.edu.socialmediallogin.data.repository_impl

import com.edu.socialmediallogin.data.source.local.RoomDao
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceIvy
import com.edu.socialmediallogin.data.source.remote.network.ApiServiceMst
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoEmbedTokenResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoStreamUrlResult
import com.edu.socialmediallogin.domain.repository.VideoRepository

class VideoRepositoryImpl(private val apiServiceMst: ApiServiceMst, private val apiServiceIvy: ApiServiceIvy, private val roomDao: RoomDao) :
    VideoRepository {

    // insert into local server
    override suspend fun insertVideos(videoEntity: List<VideoEntity>) {
        try {
            roomDao.insertVideo(videoEntity)
        } catch (e: Exception) {
            throw Exception("video is not insert in local database $e")
        }
    }

    // list of video
    override suspend fun getVideoList(subjectId: Int?): VideoListResult? {
        val videosResult = roomDao.getVideosById(subjectId)
        return if (videosResult?.id == null) {
            apiServiceMst.getVideoList(subjectId)?.result
        } else {
            videosResult
        }
    }

    // token return
    override suspend fun getEmbedToken(videoId: String?): VideoEmbedTokenResult? {
        return apiServiceMst.getVideoEmbedToken(videoId)?.result
//        val videosResult = dao.getVideosById(videoId)
//        return if (videosResult?.id == null) {
//            apiService.getVideos(subjectId).result
//        } else {
//            videosResult
//        }
    }

    // return stream url
    override suspend fun getVideoStreamingUrl(embedToken: String?): VideoStreamUrlResult? {
        return apiServiceIvy.getVideoStreamingUrl(embedToken)?.result
    }
}