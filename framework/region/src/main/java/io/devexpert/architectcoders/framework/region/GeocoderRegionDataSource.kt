package io.devexpert.architectcoders.framework.region

import android.location.Geocoder
import io.devexpert.architectcoders.domain.region.data.DEFAULT_REGION
import io.devexpert.architectcoders.domain.region.data.LocationDataSource
import io.devexpert.architectcoders.domain.region.data.RegionDataSource
import io.devexpert.architectcoders.domain.region.entities.Location
import javax.inject.Inject

internal class GeocoderRegionDataSource @Inject constructor(
    private val geocoder: Geocoder,
    private val locationDataSource: LocationDataSource
) : RegionDataSource {

    override suspend fun findLastRegion(): String =
        locationDataSource.findLastLocation()?.toRegion() ?: DEFAULT_REGION

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }

}