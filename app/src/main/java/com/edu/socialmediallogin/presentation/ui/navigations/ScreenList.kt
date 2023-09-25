package com.edu.socialmediallogin.presentation.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.edu.socialmediallogin.data.common.Constants.SUBJECT_ID
import com.edu.socialmediallogin.data.common.Constants.SUBJECT_NAME_KEY
import com.edu.socialmediallogin.data.common.Constants.VIDEO_ID_KEY

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object LoginScreen : Screen("LoginScreen")
    object RegisterScreen : Screen("RegisterScreen")
    object MainScreen: Screen("MainScreen")
    object VideoListScreen : Screen("VideoListScreen/{$SUBJECT_ID}/{$SUBJECT_NAME_KEY}")
    object VideoScreen : Screen("VideoPlayScreen/{$VIDEO_ID_KEY}")
    object TestScreen: Screen("TestScreen")
}

sealed class ScreenList(var icon: ImageVector, val route: String) {
    object HomeScreen: ScreenList(icon = Icons.Default.Home,"Home")
    object SubjectScreen : ScreenList(icon = Icons.Default.MenuBook,"Subject")
    object SearchScreen: ScreenList(icon = Icons.Default.Search,"Search")
    object ProfileScreen: ScreenList(icon = Icons.Default.PersonOutline,"Profile")
}