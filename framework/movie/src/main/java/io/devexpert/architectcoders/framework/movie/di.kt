package io.devexpert.architectcoders.framework.movie

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.devexpert.architectcoders.domain.movie.data.MoviesLocalDataSource
import io.devexpert.architectcoders.domain.movie.data.MoviesRemoteDataSource
import io.devexpert.architectcoders.framework.movie.database.MoviesRoomDataSource
import io.devexpert.architectcoders.framework.movie.network.MoviesServerDataSource

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkMovieModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: MoviesRoomDataSource): MoviesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MoviesServerDataSource): MoviesRemoteDataSource

}