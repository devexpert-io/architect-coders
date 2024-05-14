package io.devexpert.architectcoders.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import io.devexpert.architectcoders.framework.movie.database.DbMovie
import io.devexpert.architectcoders.framework.movie.database.MoviesDao

@Database(entities = [DbMovie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

