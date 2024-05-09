package io.devexpert.data

import io.devexpert.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}