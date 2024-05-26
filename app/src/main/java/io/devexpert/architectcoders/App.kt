package io.devexpert.architectcoders

import android.app.Application
import io.devexpert.architectcoders.domain.movie.domainMovieModule
import io.devexpert.architectcoders.domain.region.domainRegionModule
import io.devexpert.architectcoders.framework.core.frameworkCoreModule
import io.devexpert.architectcoders.framework.movie.frameworkMovieModule
import io.devexpert.architectcoders.framework.region.frameworkRegionModule
import io.devexpert.architectcoders.ui.detail.featureDetailModule
import io.devexpert.architectcoders.ui.home.featureHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                featureHomeModule,
                featureDetailModule,
                domainMovieModule,
                domainRegionModule,
                frameworkCoreModule,
                frameworkMovieModule,
                frameworkRegionModule
            )
        }
    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}