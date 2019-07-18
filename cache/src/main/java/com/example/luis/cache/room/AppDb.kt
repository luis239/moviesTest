package com.example.luis.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.luis.cache.beers.BeersEntity
import com.example.luis.cache.beers.dao.BeersDao
import javax.inject.Inject

@Database(
    version = 1,
    entities = [BeersEntity::class],
            exportSchema = false)

abstract class AppDb @Inject constructor() : RoomDatabase(){


    abstract fun beerDao(): BeersDao

    companion object {

        private var INSTANCE: AppDb? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDb {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDb::class.java, "app.db")
                            .fallbackToDestructiveMigration()
                            .fallbackToDestructiveMigrationFrom(1)
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .build()
                    }
                    return INSTANCE as AppDb
                }
            }
            return INSTANCE as AppDb
        }

    }

}