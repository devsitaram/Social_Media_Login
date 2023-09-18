package com.edu.socialmediallogin.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edu.socialmediallogin.presentation.screen.HomeViewScreen
import com.edu.socialmediallogin.presentation.screen.SignInViewScreen
import com.edu.socialmediallogin.presentation.screen.SignUpScreenViewScreen
import com.edu.socialmediallogin.presentation.screen.SubjectViewScreen
import com.edu.socialmediallogin.presentation.screen.VideoPlayViewScreen

@Composable
fun NavigationViewScreen(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenList.LoginScreen.route
    ) {
        composable(ScreenList.SplashScreen.route) {
//            SplashViewScreen(navHostController, getUserDevice)
        }
        composable(ScreenList.LoginScreen.route) {
            SignInViewScreen(navController)
        }
        composable(ScreenList.RegisterScreen.route) {
            SignUpScreenViewScreen(navController)
        }
        composable(ScreenList.SubjectScreen.route) {
            SubjectViewScreen(navController)
        }
        composable(ScreenList.VideoScreen.route) {
            VideoPlayViewScreen()
        }
        composable(ScreenList.AuthScreen.route) {
//            AuthScreen(navController = navController)
        }
        composable(ScreenList.HomeScreen.route) {
//                backStackEntry ->
//            val userJson = backStackEntry.arguments?.getString("user")
//            val moshi = Moshi.Builder().build()
//            val jsonAdapter = moshi.adapter(GoogleUserModel::class.java)
//            val userObject = jsonAdapter.fromJson(userJson!!)
            HomeViewScreen(navController)
        }
    }
}
