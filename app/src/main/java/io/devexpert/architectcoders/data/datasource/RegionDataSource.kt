package io.devexpert.architectcoders.data.datasource

const val DEFAULT_REGION = "US"

interface RegionDataSource {
    suspend fun findLastRegion(): String
}