package com.edu.socialmediallogin.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph() {
    val  navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(name = NAME_KEY) {
                    type = NavType.StringType
                },
                navArgument(name = AGE_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString(NAME_KEY)
            val age = navBackStackEntry.arguments?.getString(AGE_KEY)
            DetailsScreen(name = name, age = age)
        }
    }
}
