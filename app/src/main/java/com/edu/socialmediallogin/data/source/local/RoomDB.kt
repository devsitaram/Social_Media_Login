package com.edu.socialmediallogin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.socialmediallogin.data.common.Constants.DATABASE_NAME

@Database(entities = [UserEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabaseInstance(context: Context): RoomDB {
            return if (INSTANCE != null ){
                INSTANCE!!
            } else {
                INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java, DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}