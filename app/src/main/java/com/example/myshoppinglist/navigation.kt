package com.example.myshoppinglist

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(
    viewModel: listViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        // Home Screen
        composable(route = Screens.HomeScreen.route) {
            HomeView(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Add/Edit Screen
        composable(
            route = Screens.AddEditScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = "0"
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            AddEditScreen(id = id.toLong(), navController = navController, viewModel = viewModel)
        }
    }

}
