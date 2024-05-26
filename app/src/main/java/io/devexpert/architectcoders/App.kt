package io.devexpert.architectcoders

import android.app.Application
import io.devexpert.architectcoders.domain.movie.DomainMovieModule
import io.devexpert.architectcoders.domain.region.DomainRegionModule
import io.devexpert.architectcoders.framework.core.frameworkCoreModule
import io.devexpert.architectcoders.framework.movie.FrameworkMovieModule
import io.devexpert.architectcoders.framework.region.FrameworkRegionModule
import io.devexpert.architectcoders.framework.region.frameworkRegionModule
import io.devexpert.architectcoders.ui.detail.FeatureDetailModule
import io.devexpert.architectcoders.ui.home.FeatureHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                FeatureHomeModule().module,
                FeatureDetailModule().module,
                DomainMovieModule().module,
                DomainRegionModule().module,
                frameworkCoreModule,
                FrameworkMovieModule().module,
                frameworkRegionModule,
                FrameworkRegionModule().module
            )
        }
    }
}

val appModule = module {
    single(named("apiKey")) { BuildConfig.TMDB_API_KEY }
}