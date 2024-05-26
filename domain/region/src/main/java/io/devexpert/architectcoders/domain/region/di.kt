package io.devexpert.architectcoders.domain.region

import io.devexpert.architectcoders.domain.region.data.RegionRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainRegionModule = module {
    factoryOf(::RegionRepository)
}