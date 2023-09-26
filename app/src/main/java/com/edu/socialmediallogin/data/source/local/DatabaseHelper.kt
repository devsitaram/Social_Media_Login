package com.edu.socialmediallogin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.edu.socialmediallogin.data.common.Constants.DATABASE_NAME
import com.edu.socialmediallogin.data.common.util.json_convertoer.TypeConverter

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
@TypeConverters(TypeConverter::class)
abstract class DatabaseHelper : RoomDatabase() {
    abstract fun userDao(): RoomDao

    companion object {
        fun getDatabaseInstance(context: Context): DatabaseHelper {
            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
        }
    }
}