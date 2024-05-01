package io.devexpert.architectcoders.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.data.RegionRepository
import io.devexpert.architectcoders.ui.screens.detail.DetailScreen
import io.devexpert.architectcoders.ui.screens.detail.DetailViewModel
import io.devexpert.architectcoders.ui.screens.home.HomeScreen
import io.devexpert.architectcoders.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as Application

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {

            HomeScreen(
                viewModel {
                    HomeViewModel(MoviesRepository(RegionRepository(app)))
                },
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
                viewModel { DetailViewModel(MoviesRepository(RegionRepository(app)), movieId) },
                onBack = { navController.popBackStack() })
        }
    }
}