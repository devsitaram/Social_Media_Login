package com.edu.socialmediallogin.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.presentation.Navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashViewScreen(getUserDevice: String?, navController: NavHostController) {

    var isPlaying by remember { mutableStateOf(true) }
    var isTextVisible by remember { mutableStateOf(false) }

    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_animation))

    val progress by animateLottieCompositionAsState(
        composition = compositionResult.value,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    LaunchedEffect(!isTextVisible) {
        delay(3200) // Delay for 4 seconds after stopping the animation
        isTextVisible = true
    }
    LaunchedEffect(isPlaying) {
        delay(4000)
        isPlaying = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = compositionResult.value,
            progress = progress,
            modifier = Modifier.fillMaxSize()
        )

//        if (isTextVisible) {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome to Gameyo",
//                    style = TextStyle(
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Normal,
//                        fontFamily = FontFamily.Cursive,
//                        color = Color.Unspecified
//                    )
//                )
//            }
//        }
    }

    LaunchedEffect(key1 = isTextVisible) {
        if (isTextVisible) {
            if (getUserDevice.isNullOrEmpty() || getUserDevice != "this") {
                navController.navigate(Screen.LoginScreen.route)
            } else {
                navController.navigate(Screen.MainScreen.route)
            }
        }
    }
}