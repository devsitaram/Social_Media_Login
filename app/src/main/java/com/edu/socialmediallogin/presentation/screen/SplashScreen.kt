package com.edu.socialmediallogin.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.presentation.components.PainterImageView
import com.edu.socialmediallogin.presentation.compose.ScreenList

@Composable
fun SplashViewScreen(getUserDevice: String?, navController: NavHostController) {
    Box(modifier= Modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.Center){
        PainterImageView(painter = painterResource(id = R.mipmap.ic_app_logo))
    }

//    LaunchedEffect(key1 = Unit){
//        Log.e("getUserDevice", "getUserDevice : $getUserDevice")
        if (getUserDevice == null || getUserDevice != "this") {
            navController.navigate(ScreenList.LoginScreen.route)
        } else {
            navController.navigate(ScreenList.HomeScreen.route)
        }
//    }
}