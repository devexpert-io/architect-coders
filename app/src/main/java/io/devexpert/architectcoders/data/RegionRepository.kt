package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}