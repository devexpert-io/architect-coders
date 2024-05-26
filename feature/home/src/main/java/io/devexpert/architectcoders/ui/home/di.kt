package io.devexpert.architectcoders.ui.home

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {
    viewModelOf(::HomeViewModel)
}