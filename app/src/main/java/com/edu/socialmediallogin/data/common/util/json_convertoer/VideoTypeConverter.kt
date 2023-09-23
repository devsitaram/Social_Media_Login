package com.edu.socialmediallogin.data.common.util.json_convertoer

import androidx.room.TypeConverter
import com.edu.socialmediallogin.data.source.remote.pojo.video.ChaptersItem
import com.edu.socialmediallogin.data.source.remote.pojo.video.TopicsItem
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideosItem
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class VideoTypeConverter {

    // ChaptersItem
    @TypeConverter
    fun fromChaptersItemList(listOfChapter: List<ChaptersItem?>?): String? {
        return Gson().toJson(listOfChapter)
    }

    @TypeConverter
    fun toChaptersItemList(chaptersJson: String?): List<ChaptersItem?>? {
        return Gson().fromJson(chaptersJson, object : TypeToken<List<ChaptersItem?>>() {}.type)
    }

    // TopicsItem
    @TypeConverter
    fun fromTopicsItemList(listOfTopics: List<TopicsItem?>?): String? {
        return Gson().toJson(listOfTopics)
    }

    @TypeConverter
    fun toTopicsItemList(topicsJson: String?): List<TopicsItem?>? {
        return Gson().fromJson(topicsJson, object : TypeToken<List<TopicsItem?>>() {}.type)
    }

    // VideosItem
    @TypeConverter
    fun fromVideosItemList(listOfVideos: List<VideosItem?>?): String? {
        return Gson().toJson(listOfVideos)
    }

    @TypeConverter
    fun toVideosItemList(videosJson: String?): List<VideosItem?>? {
        return Gson().fromJson(videosJson, object : TypeToken<List<VideosItem?>>() {}.type)
    }
}