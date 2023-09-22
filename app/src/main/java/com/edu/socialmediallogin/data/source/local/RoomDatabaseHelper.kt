package com.edu.socialmediallogin.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * this is the Room Database which is abstract class
 * @param entities: list of entities class*
 * @param version
 *        1: create database one subject table
 *        2: add another user table (migrate)
 *        3: update the user table (add the column)
 *        4: new methods implement in dao and entity id auto generate changes
 * @param exportSchema: false
 * implement RoomDatabase
 */
@Database(entities = [SubjectEntity::class, UserEntity::class], version = 5, exportSchema = false)
abstract class RoomDatabaseHelper : RoomDatabase() {
    abstract fun userDao(): Dao

//    companion object {
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