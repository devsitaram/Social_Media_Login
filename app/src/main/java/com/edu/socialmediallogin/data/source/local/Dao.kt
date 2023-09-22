package com.edu.socialmediallogin.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.edu.socialmediallogin.data.source.remote.pojo.user.UserProfiles

@SuppressWarnings("AndroidUnresolvedRoomSqlReference")
@Dao
interface Dao {

    // user
    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertUserProfile(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    suspend fun getUserProfiles(): UserProfiles?

    // Subject
    @Upsert // insert and update both
    suspend fun insertSubject(subject: List<SubjectEntity>)

//    @Query("SELECT * FROM subject")
//    suspend fun getAllSubjects(): List<SubjectItem>

    @Query("DELETE FROM subject WHERE id = :id")
    suspend fun deleteSubject(id: Int)

}