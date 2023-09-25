package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoUrlResult

interface VideoRepository {
    suspend fun insertVideos(videoEntity: List<VideoEntity>)
    suspend fun getVideoDetails(subjectId: Int?):  VideoResult?
    suspend fun getVideoUrl(videoId: String?): VideoUrlResult?
}