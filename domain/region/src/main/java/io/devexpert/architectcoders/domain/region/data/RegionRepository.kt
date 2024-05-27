package io.devexpert.architectcoders.domain.region.data

import javax.inject.Inject

class RegionRepository @Inject constructor(private val regionDataSource: RegionDataSource) {

    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()

}