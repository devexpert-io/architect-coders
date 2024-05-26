package io.devexpert.architectcoders.framework.core

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module

val frameworkCoreModule = module {
    single { Room.databaseBuilder(get(), MoviesDatabase::class.java, "movies.db").build() }
    factory { get<MoviesDatabase>().moviesDao() }
    single { MoviesClient(get(named("apiKey"))).instance }
}