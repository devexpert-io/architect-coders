package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.domain.movie.data.MoviesLocalDataSource
import io.devexpert.architectcoders.domain.movie.data.MoviesRemoteDataSource
import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.entities.Movie
import io.devexpert.architectcoders.domain.movie.sampleMovies
import io.devexpert.architectcoders.domain.region.data.DEFAULT_REGION
import io.devexpert.architectcoders.domain.region.data.RegionDataSource
import io.devexpert.architectcoders.domain.region.data.RegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildMoviesRepositoryWith(
    localData: List<Movie> = emptyList(),
    remoteData: List<Movie> = emptyList()
): MoviesRepository {
    val regionDataSource = FakeRegionDataSource()
    val regionRepository = RegionRepository(regionDataSource)
    val localDataSource = FakeLocalDataSource().apply { inMemoryMovies.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { movies = remoteData }
    return MoviesRepository(regionRepository, localDataSource, remoteDataSource)
}

class FakeLocalDataSource : MoviesLocalDataSource {

    val inMemoryMovies = MutableStateFlow<List<Movie>>(emptyList())

    override val movies = inMemoryMovies

    override fun findMovieById(id: Int): Flow<Movie?> =
        inMemoryMovies.map { it.firstOrNull { movie -> movie.id == id } }

    override suspend fun save(movies: List<Movie>) {
        inMemoryMovies.value = movies
    }
}

class FakeRemoteDataSource : MoviesRemoteDataSource {

    var movies = sampleMovies(1, 2, 3, 4)

    override suspend fun fetchPopularMovies(region: String) = movies

    override suspend fun findMovieById(id: Int): Movie = movies.first { it.id == id }
}

class FakeRegionDataSource : RegionDataSource {

    var region = DEFAULT_REGION

    override suspend fun findLastRegion(): String = region

}