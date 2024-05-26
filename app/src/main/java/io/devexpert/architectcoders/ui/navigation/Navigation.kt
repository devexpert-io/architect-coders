package io.devexpert.architectcoders.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.devexpert.architectcoders.ui.detail.DetailScreen
import io.devexpert.architectcoders.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {

            HomeScreen(
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
                koinViewModel(parameters = { parametersOf(movieId) }),
                onBack = { navController.popBackStack() })
        }
    }
}