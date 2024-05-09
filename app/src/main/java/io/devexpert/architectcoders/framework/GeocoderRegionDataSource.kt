package io.devexpert.architectcoders.framework

import android.location.Geocoder
import io.devexpert.architectcoders.ui.common.getFromLocationCompat
import io.devexpert.data.datasource.DEFAULT_REGION
import io.devexpert.data.datasource.LocationDataSource
import io.devexpert.data.datasource.RegionDataSource
import io.devexpert.domain.Location

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