package io.devexpert.architectcoders.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.devexpert.architectcoders.data.Movie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movie")
    suspend fun fetchPopularMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun findMovieById(id: Int): Movie?

    @Query("SELECT COUNT(id) FROM Movie")
    suspend fun countMovies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movies: List<Movie>)

}