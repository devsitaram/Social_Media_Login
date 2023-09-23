package com.edu.socialmediallogin.data.common.util.json_convertoer

import androidx.room.TypeConverter
import com.edu.socialmediallogin.data.source.remote.pojo.subject.AssetType
import com.edu.socialmediallogin.data.source.remote.pojo.subject.Level
import com.edu.socialmediallogin.data.source.remote.pojo.subject.StudentSubject
import com.google.gson.Gson

class SubjectTypeConverter {
    @TypeConverter
    fun fromStudentSubject(studentSubject: StudentSubject?): String? {
        return studentSubject?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toStudentSubject(studentSubjectJson: String?): StudentSubject? {
        return studentSubjectJson?.let { Gson().fromJson(it, StudentSubject::class.java) }
    }

    // level
    @TypeConverter
    fun fromLevel(level: Level?): String? {
        return level?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toLevel(levelJson: String?): Level? {
        return levelJson?.let { Gson().fromJson(it, Level::class.java) }
    }

    // Asset Type
    @TypeConverter
    fun fromAssetType(assetType: AssetType?): String? {
        return assetType?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toAssetType(assetTypeJson: String?): AssetType? {
        return assetTypeJson?.let { Gson().fromJson(it, AssetType::class.java) }
    }
}