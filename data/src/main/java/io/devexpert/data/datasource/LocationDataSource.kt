package io.devexpert.data.datasource

import io.devexpert.domain.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}