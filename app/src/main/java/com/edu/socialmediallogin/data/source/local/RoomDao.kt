package com.edu.socialmediallogin.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.edu.socialmediallogin.data.source.remote.pojo.subject.SubjectResult
import com.edu.socialmediallogin.data.source.remote.pojo.user.ProfileResult
import com.edu.socialmediallogin.data.source.remote.pojo.video.VideoListResult

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
interface RoomDao {

    // user
    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertUserProfile(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun getUserProfiles(): ProfileResult?

    // Subject
    @Upsert // insert and update both
    suspend fun insertSubject(listOfSubject: List<SubjectEntity>)

    @Query("SELECT * FROM subjects")
    suspend fun getSubject(): List<SubjectResult>?

    @Query("DELETE FROM subjects WHERE subjectId = :id")
    suspend fun deleteSubject(id: Int)

    // video
    @Upsert // insert and update both
    suspend fun insertVideo(listOfVideo: List<VideoEntity>)

    @Query("SELECT * FROM videos")
    suspend fun getVideos(): VideoListResult?

    @Query("SELECT * FROM videos WHERE id = :subjectId")
    suspend fun getVideosById(subjectId: Int?): VideoListResult?

}