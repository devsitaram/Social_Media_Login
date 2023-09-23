package com.edu.socialmediallogin.domain.repository

import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoResult
import dagger.Module

interface VideoRepository {
    suspend fun insertVideos(videoEntity: List<VideoEntity>)
    suspend fun getVideoDetails():  VideoResult?
}