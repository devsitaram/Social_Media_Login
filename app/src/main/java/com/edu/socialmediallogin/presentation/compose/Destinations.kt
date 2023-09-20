package com.edu.socialmediallogin.presentation.compose

import com.edu.socialmediallogin.navigate.AGE_KEY
import com.edu.socialmediallogin.navigate.NAME_KEY
import com.edu.socialmediallogin.navigate.Screen

const val SUBJECT_NAME_KEY = "subject_name"
const val SUBJECT_DESC_KEY = "subject_descriptions"
const val VIDEO_URL_KEY = "video_url"

sealed class ScreenList(val route: String) {
    object SplashScreen : ScreenList("SplashScreen")
    object LoginScreen : ScreenList("LoginScreen")
    object RegisterScreen : ScreenList("RegisterScreen")
    object HomeScreen: ScreenList("HomeScreen")
    object SubjectScreen : ScreenList("SubjectScreen")
    object VideoScreen : ScreenList("VideoScreen/{$SUBJECT_NAME_KEY}/{$SUBJECT_DESC_KEY}/{$VIDEO_URL_KEY}")
    object ProfileScreen: ScreenList("ProfileScreen")
    object SearchScreen: ScreenList("SearchScreen")
}