package com.example.seriados.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.seriados.model.Episodios
import com.example.seriados.model.Series

@Database(
    entities = [
        Series::class,
        Episodios::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
    abstract fun episodiosDao(): EpisodiosDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as AppDatabase
        }
    }

}