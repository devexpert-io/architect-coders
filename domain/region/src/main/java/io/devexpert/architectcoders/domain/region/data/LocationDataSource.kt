package io.devexpert.architectcoders.domain.region.data

import io.devexpert.architectcoders.domain.region.entities.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}