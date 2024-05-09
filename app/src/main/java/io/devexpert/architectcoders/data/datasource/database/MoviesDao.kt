package io.devexpert.architectcoders.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM DbMovie")
    fun fetchPopularMovies(): Flow<List<DbMovie>>

    @Query("SELECT * FROM DbMovie WHERE id = :id")
    fun findMovieById(id: Int): Flow<DbMovie?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movies: List<DbMovie>)

}