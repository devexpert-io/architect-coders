package io.devexpert.architectcoders.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.devexpert.architectcoders.domain.movie.sampleMovies
import io.devexpert.architectcoders.ui.common.LOADING_INDICATOR_TAG
import io.devexpert.architectcoders.ui.common.Result
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Loading,
                onMovieClick = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertExists()
    }

    @Test
    fun whenErrorState_showError(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Error(RuntimeException("An error occurred")),
                onMovieClick = {}
            )
        }

        onNodeWithText("An error occurred").assertExists()
    }

    @Test
    fun whenSuccessState_moviesAreShown(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                state = Result.Success(sampleMovies(1, 2, 3)),
                onMovieClick = {}
            )
        }

        onNodeWithText("Title 2").assertExists()
    }

    @Test
    fun whenMovieClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clickedMovieId = -1
        val movies = sampleMovies(1, 2, 3)
        setContent {
            HomeScreen(
                state = Result.Success(movies),
                onMovieClick = { clickedMovieId = it.id }
            )
        }

        onNodeWithText("Title 2").performClick()

        assertEquals(2, clickedMovieId)
    }
}