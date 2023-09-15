package com.edu.socialmediallogin.presentation.compose

sealed class ScreenList(val route: String) {
    object SplashScreen : ScreenList("SplashScreen")
    object LoginScreen : ScreenList("LoginScreen")
    object RegisterScreen : ScreenList("RegisterScreen")
    object HomeScreen: ScreenList("HomeScreen/{user}")
    object SubjectScreen : ScreenList("SubjectScreen")
    object VideoScreen : ScreenList("VideoScreen")
    object AuthScreen: ScreenList("AuthScreen")
}

//object Destinations {
//    const val Auth="Auth"
//    const val Home = "home/{user}"
//    const val VPlayer ="vPlayer"
//}