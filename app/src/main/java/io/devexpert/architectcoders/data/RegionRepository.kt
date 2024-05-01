package io.devexpert.architectcoders.data

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.devexpert.architectcoders.ui.common.getFromLocationCompat
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

const val DEFAULT_REGION = "US"

class RegionRepository(app: Application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)
    private val geocoder = Geocoder(app)

    suspend fun findLastRegion(): String =
        fusedLocationClient.lastLocation()?.toRegion() ?: DEFAULT_REGION

    @SuppressLint("MissingPermission")
    private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resume(null)
            }
        }
    }

    private suspend fun Location.toRegion(): String {
        val addresses = geocoder.getFromLocationCompat(latitude, longitude, 1)
        val region = addresses.firstOrNull()?.countryCode
        return region ?: DEFAULT_REGION
    }
}