package io.devexpert.architectcoders.data.datasource

import android.location.Geocoder
import android.location.Location
import io.devexpert.architectcoders.ui.common.getFromLocationCompat

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String
}

class GeocoderRegionDataSource(
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