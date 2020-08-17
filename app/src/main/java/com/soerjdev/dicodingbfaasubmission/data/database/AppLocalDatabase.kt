package com.soerjdev.dicodingbfaasubmission.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteModel::class],
    version = 2,
    exportSchema = true
) abstract class AppLocalDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: AppLocalDatabase? = null

        fun getDatabase(context: Context): AppLocalDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppLocalDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}