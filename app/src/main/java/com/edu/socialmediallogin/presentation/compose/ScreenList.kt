package com.edu.socialmediallogin.presentation.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

const val SUBJECT_NAME_KEY = "subject_name"
const val SUBJECT_DESC_KEY = "subject_descriptions"
const val VIDEO_URL_KEY = "video_url"

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object LoginScreen : Screen("LoginScreen")
    object RegisterScreen : Screen("RegisterScreen")
    object MainScreen: Screen("MainScreen")
    object VideoScreen : Screen("VideoScreen/{$SUBJECT_NAME_KEY}/{$SUBJECT_DESC_KEY}/{$VIDEO_URL_KEY}")
    object TestScreen: Screen("TestScreen")
}

sealed class ScreenList(var icon: ImageVector, val route: String) {
    object HomeScreen: ScreenList(icon = Icons.Default.Home,"Home")
    object SubjectScreen : ScreenList(icon = Icons.Default.MenuBook,"Subject")
    object SearchScreen: ScreenList(icon = Icons.Default.Search,"Search")
    object ProfileScreen: ScreenList(icon = Icons.Default.PersonOutline,"Profile")
}