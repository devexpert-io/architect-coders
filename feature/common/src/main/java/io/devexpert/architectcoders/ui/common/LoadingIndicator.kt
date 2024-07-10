package io.devexpert.architectcoders.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val LOADING_INDICATOR_TAG = "loadingIndicator"

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LOADING_INDICATOR_TAG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}