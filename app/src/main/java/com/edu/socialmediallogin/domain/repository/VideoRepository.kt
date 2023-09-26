package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoEmbedTokenResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoStreamUrlResult

interface VideoRepository {
    suspend fun insertVideos(videoEntity: List<VideoEntity>)
    suspend fun getVideoList(subjectId: Int?):  VideoListResult?
    suspend fun getEmbedToken(videoId: String?): VideoEmbedTokenResult?
    suspend fun getVideoStreamingUrl(embedToken: String?): VideoStreamUrlResult?
}