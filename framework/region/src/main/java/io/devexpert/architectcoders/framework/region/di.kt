package io.devexpert.architectcoders.framework.region

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import io.devexpert.architectcoders.domain.region.data.LocationDataSource
import io.devexpert.architectcoders.domain.region.data.RegionDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkRegionModule = module {
    factoryOf(::PlayServicesLocationDataSource) bind LocationDataSource::class
    factory { LocationServices.getFusedLocationProviderClient(get<Context>()) }
    factoryOf(::GeocoderRegionDataSource) bind RegionDataSource::class
    factory { Geocoder(get()) }
}