package com.edu.socialmediallogin.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edu.socialmediallogin.presentation.screen.HomeViewScreen
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
        startDestination = if (getUserDevice.isNullOrEmpty() || getUserDevice != "this") {
            ScreenList.LoginScreen.route // LoginScreen
        } else {
            ScreenList.HomeScreen.route
        }
    ) {
//        composable(ScreenList.SplashScreen.route) {
//            SplashViewScreen(getUserDevice, navController)
//        }
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
            HomeViewScreen(navController, checked, onCheckedChange)
        }
    }
}
