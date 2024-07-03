package io.devexpert.architectcoders.domain.movie.usecases

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindMovieByIdUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val movieFlow = flowOf(sampleMovie(1))
        val useCase = FindMovieByIdUseCase(mock {
            on { findMovieById(1) } doReturn movieFlow
        })

        val result = useCase(1)

        assertEquals(movieFlow, result)
    }
}