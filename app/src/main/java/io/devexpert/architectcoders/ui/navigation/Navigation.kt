package io.devexpert.architectcoders.ui.navigation

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.location.LocationServices
import io.devexpert.architectcoders.App
import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.data.RegionRepository
import io.devexpert.architectcoders.data.datasource.GeocoderRegionDataSource
import io.devexpert.architectcoders.data.datasource.MoviesRoomDataSource
import io.devexpert.architectcoders.data.datasource.MoviesServerDataSource
import io.devexpert.architectcoders.data.datasource.PlayServicesLocationDataSource
import io.devexpert.architectcoders.data.datasource.remote.MoviesClient
import io.devexpert.architectcoders.ui.screens.detail.DetailScreen
import io.devexpert.architectcoders.ui.screens.detail.DetailViewModel
import io.devexpert.architectcoders.ui.screens.home.HomeScreen
import io.devexpert.architectcoders.ui.screens.home.HomeViewModel
import io.devexpert.architectcoders.usecases.FetchMoviesUseCase
import io.devexpert.architectcoders.usecases.FindMovieByIdUseCase
import io.devexpert.architectcoders.usecases.ToggleFavoriteUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val moviesRepository = remember {
        MoviesRepository(
            regionRepository = RegionRepository(
                GeocoderRegionDataSource(
                    Geocoder(app),
                    PlayServicesLocationDataSource(
                        LocationServices.getFusedLocationProviderClient(app)
                    )
                )
            ),
            localDataSource = MoviesRoomDataSource(app.db.moviesDao()),
            remoteDataSource = MoviesServerDataSource(MoviesClient.instance)
        )
    }

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {

            HomeScreen(
                viewModel { HomeViewModel(FetchMoviesUseCase(moviesRepository)) },
                onMovieClick = { movie ->
                    navController.navigate(NavScreen.Detail.createRoute(movie.id))
                }
            )
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.MovieId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                viewModel {
                    DetailViewModel(
                        movieId,
                        FindMovieByIdUseCase(moviesRepository),
                        ToggleFavoriteUseCase(moviesRepository)
                    )
                },
                onBack = { navController.popBackStack() })
        }
    }
}