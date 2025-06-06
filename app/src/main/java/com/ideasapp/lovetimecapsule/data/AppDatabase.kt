package com.ideasapp.lovetimecapsule.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CapsuleDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun CapsuleDao(): CapsuleDao

    companion object {
        private var INSTANCE:AppDatabase? = null
        private val LOCK = Any()
        const val DATABASE_NAME = "capsules.db"

        fun getInstance(application :Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db =  Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}