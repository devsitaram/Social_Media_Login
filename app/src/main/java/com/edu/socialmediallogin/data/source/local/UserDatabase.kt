package com.edu.socialmediallogin.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.socialmediallogin.data.common.Constants.DATABASE_NAME

@Database(entities = [SubjectEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabaseInstance(context: Context): UserDatabase {
//            return if (INSTANCE != null) {
//                INSTANCE!!
//            } else {
//                INSTANCE ?: synchronized(this) {
//                    val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        UserDatabase::class.java,
//                        DATABASE_NAME
//                    ).build()
//                    INSTANCE = instance
//                    instance
//                }
//            }
//        }
//    }
}