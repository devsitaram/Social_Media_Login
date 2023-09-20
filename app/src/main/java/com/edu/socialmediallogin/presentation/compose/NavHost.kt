package com.edu.socialmediallogin.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edu.socialmediallogin.presentation.screen.HomeViewScreen
import com.edu.socialmediallogin.presentation.screen.ProfileViewScreen
import com.edu.socialmediallogin.presentation.screen.SearchViewScreen
import com.edu.socialmediallogin.presentation.screen.SignInViewScreen
import com.edu.socialmediallogin.presentation.screen.SignUpScreenViewScreen
import com.edu.socialmediallogin.presentation.screen.SplashViewScreen
import com.edu.socialmediallogin.presentation.screen.SubjectViewScreen
import com.edu.socialmediallogin.presentation.screen.VideoPlayViewScreen

@Composable
fun NavigationViewScreen(checked: Boolean, onCheckedChange: () -> Unit, getUserDevice: String?) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = // ScreenList.SearchScreen.route // ScreenList.SplashScreen.route
        if (getUserDevice.isNullOrEmpty()) {
            ScreenList.LoginScreen.route
        } else {
            ScreenList.HomeScreen.route
        }
    ) {
        composable(ScreenList.SplashScreen.route) {
            SplashViewScreen(getUserDevice, navController)
        }
        composable(ScreenList.LoginScreen.route) {
            SignInViewScreen(navController)
        }
        composable(ScreenList.RegisterScreen.route) {
            SignUpScreenViewScreen(navController)
        }
        composable(ScreenList.HomeScreen.route) {
            HomeViewScreen(navController, checked, onCheckedChange)
        }
        composable(ScreenList.SubjectScreen.route) {
            SubjectViewScreen(navController)
        }
        composable(
            route = ScreenList.VideoScreen.route,
            arguments = listOf(
                navArgument(name = SUBJECT_NAME_KEY) {
                    type = NavType.StringType
                },
                navArgument(name = SUBJECT_DESC_KEY) {
                    type = NavType.StringType
                },
                navArgument(name = VIDEO_URL_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString(SUBJECT_NAME_KEY)
            val description = navBackStackEntry.arguments?.getString(SUBJECT_DESC_KEY)
            val videoUrl = navBackStackEntry.arguments?.getString(VIDEO_URL_KEY)
            VideoPlayViewScreen(title = name, description = description, videoUrls = videoUrl)
        }
        composable(ScreenList.ProfileScreen.route) {
            ProfileViewScreen(navController)
        }
        composable(ScreenList.SearchScreen.route) {
            SearchViewScreen(navController)
        }
    }
}
