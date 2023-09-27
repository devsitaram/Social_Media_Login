package com.edu.socialmediallogin.presentation.ui.navigations

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edu.socialmediallogin.data.common.Constants.SUBJECT_ID
import com.edu.socialmediallogin.data.common.Constants.SUBJECT_NAME_KEY
import com.edu.socialmediallogin.data.common.Constants.VIDEO_ID_KEY
import com.edu.socialmediallogin.test.TestingViewScreen
import com.edu.socialmediallogin.presentation.ui.components.TextView
import com.edu.socialmediallogin.presentation.ui.components.VectorIconView
import com.edu.socialmediallogin.presentation.ui.screen.HomeViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.ProfileViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.SearchViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.SubjectViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.video.VideoListViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.video.VideoPlayViewScreen
import com.edu.socialmediallogin.presentation.ui.screen.video.VideoViewScreen
import com.edu.socialmediallogin.ui.theme.pink
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainViewScreen(checked: Boolean, navHostController: NavHostController, onCheckedChange: () -> Unit) {
    val navController = rememberNavController()

    val pages = listOf(
        ScreenList.HomeScreen,
        ScreenList.SubjectScreen,
        ScreenList.SearchScreen,
        ScreenList.ProfileScreen,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                pages.forEach { screen ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .background(color = Color.White),
                        icon = {
                            VectorIconView(imageVector = screen.icon,
                                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    pink // Change to your desired color
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        label = {
                            TextView(
                                text = screen.route,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    pink // Change to your desired color
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        ButtonNavigationViewScreen(navController, checked, onCheckedChange, navHostController)
    }
}

@Composable
fun ButtonNavigationViewScreen(
    navController: NavHostController,
    checked: Boolean,
    onCheckedChange: () -> Unit,
    navHostController: NavHostController
) {
    NavHost(navController = navController, startDestination = ScreenList.HomeScreen.route) {
        composable(ScreenList.HomeScreen.route) {
            HomeViewScreen(navController, checked, onCheckedChange)
        }
        composable(ScreenList.SubjectScreen.route) {
            SubjectViewScreen(navController)
        }
        composable(ScreenList.SearchScreen.route) {
            SearchViewScreen(navController)
        }
        composable(ScreenList.ProfileScreen.route) {
            ProfileViewScreen(navController, navHostController)
        }
        // other screen
        composable(
            route = Screen.VideoListScreen.route,
            arguments = listOf(
                navArgument(name = SUBJECT_NAME_KEY) {
                    type = NavType.StringType
                },
                navArgument(name = SUBJECT_ID) {
                    type = NavType.StringType
                },
            )
        ) { navBackStackEntry ->
            val subjectId = navBackStackEntry.arguments?.getString(SUBJECT_ID)?.toInt()
            val subject = navBackStackEntry.arguments?.getString(SUBJECT_NAME_KEY)
            VideoListViewScreen(subjectId, subject, navController)
        }
        composable(
            route = Screen.VideoScreen.route,
            arguments = listOf(
                navArgument(name = VIDEO_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val videoId = navBackStackEntry.arguments?.getString(VIDEO_ID_KEY)
//            VideoPlayViewScreen(videoId = videoId)
            VideoViewScreen(videoId)
        }
        composable(Screen.TestScreen.route) {
//            TestingViewScreen(navController)
        }
    }
}
