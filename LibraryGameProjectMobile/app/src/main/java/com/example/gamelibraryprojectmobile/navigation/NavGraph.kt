package com.example.gamelibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamelibrary.ui.screens.*

@Composable
fun AppNavGraph(
    navController: NavHostController,
    onGameClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier  // параметр
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Экраны нижней панели
        composable(Screen.Home.route) {
            HomeScreen(onGameClick = onGameClick, onSearchClick = onSearchClick)
        }
        composable(Screen.Library.route) {
            LibraryScreen(onGameClick = onGameClick)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("gameId")?.toIntOrNull() ?: 1
            DetailScreen(gameId = id, onBack = { navController.popBackStack() })
        }
        composable(Screen.Search.route) {
            SearchScreen(onBack = { navController.popBackStack() })
        }
    }
}