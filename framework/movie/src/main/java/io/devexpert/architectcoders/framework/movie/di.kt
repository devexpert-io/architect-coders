package io.devexpert.architectcoders.framework.movie

import io.devexpert.architectcoders.domain.movie.data.MoviesLocalDataSource
import io.devexpert.architectcoders.domain.movie.data.MoviesRemoteDataSource
import io.devexpert.architectcoders.framework.movie.database.MoviesRoomDataSource
import io.devexpert.architectcoders.framework.movie.network.MoviesServerDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkMovieModule = module {
    factoryOf(::MoviesRoomDataSource) bind MoviesLocalDataSource::class
    factoryOf(::MoviesServerDataSource) bind MoviesRemoteDataSource::class
}