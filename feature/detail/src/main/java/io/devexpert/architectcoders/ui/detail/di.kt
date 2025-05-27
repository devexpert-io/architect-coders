package io.devexpert.architectcoders.ui.detail

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featureDetailModule = module {
    viewModel { (id: Int) -> DetailViewModel(id, get(), get()) }
}