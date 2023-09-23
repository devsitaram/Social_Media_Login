package com.edu.socialmediallogin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.edu.socialmediallogin.data.common.Constants.DATABASE_NAME
import com.edu.socialmediallogin.data.common.util.json_convertoer.SubjectTypeConverter
import com.edu.socialmediallogin.data.common.util.json_convertoer.VideoTypeConverter

/**
 * this is the Room Database which is abstract class
 * @param entities: list of entities class*
 * @param version
 *        1: create database one subject table @Database
 *        2: add another user table (migrate)
 *        3: update the user table (add the column)
 *        4: new methods implement in dao and entity id auto generate changes
 *        5: adding json type converter @TypeConverters
 * @param exportSchema: false
 * implement RoomDatabase
 */
@Database(
    entities = [SubjectEntity::class, UserEntity::class, VideoEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(SubjectTypeConverter::class, VideoTypeConverter::class)
abstract class RoomDatabaseHelper : RoomDatabase() {
    abstract fun userDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabaseHelper? = null

//        fun getDatabaseInstance(context: Context): RoomDatabaseHelper {
//            return if (INSTANCE != null) {
//                INSTANCE!!
//            } else {
//                INSTANCE ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        RoomDatabaseHelper::class.java,
//                        DATABASE_NAME
//                    ).fallbackToDestructiveMigration().build()
//                    INSTANCE = instance
//                    instance
//                }
//            }
//        }
    }
}