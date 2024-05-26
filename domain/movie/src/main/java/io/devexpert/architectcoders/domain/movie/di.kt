package io.devexpert.architectcoders.domain.movie

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.usecases.FetchMoviesUseCase
import io.devexpert.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import io.devexpert.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val domainMovieModule = module {
    factoryOf(::MoviesRepository)
    factoryOf(::FetchMoviesUseCase)
    factoryOf(::FindMovieByIdUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}