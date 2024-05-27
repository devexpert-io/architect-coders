package io.devexpert.architectcoders.framework.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.devexpert.architectcoders.framework.movie.network.MoviesService
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        MoviesDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    fun provideMoviesDao(db: MoviesDatabase) = db.moviesDao()

    @Provides
    @Singleton
    fun provideMoviesService(@Named("apiKey") apiKey: String): MoviesService = MoviesClient(apiKey).instance
}