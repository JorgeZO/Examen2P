package com.example.jzunigamusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jzunigamusicapp.ui.screens.DetailScreen
import com.example.jzunigamusicapp.ui.screens.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onAlbumClick = { albumId ->
                    navController.navigate(DetailRoute(albumId = albumId))
                }
            )
        }
        composable<DetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<DetailRoute>()
            DetailScreen(
                albumId = route.albumId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
