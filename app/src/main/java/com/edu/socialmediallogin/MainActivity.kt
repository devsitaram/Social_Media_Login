package com.edu.socialmediallogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.edu.socialmediallogin.navigate.SetupNavGraph
import com.edu.socialmediallogin.presentation.compose.NavigationViewScreen
import com.edu.socialmediallogin.ui.theme.SocialMedialLoginTheme
//demo1004@mst.sg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create the Shared Preferences
        val getSharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
        val getUserDevice = getSharedPreferences.getString("access_token", "")

        var darkMode by mutableStateOf(false)

        setContent {
            SocialMedialLoginTheme(darkTheme = darkMode) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationViewScreen(checked = darkMode, onCheckedChange = { darkMode = !darkMode}, getUserDevice)
                }
            }
        }
    }
}


//NavHost(navController = navController, startDestination = "sign_in") {
//    composable("sign_in") {
//        val googleAuthUiClient by lazy {
//            GoogleAuthUiClient(
//                context = applicationContext,
//                oneTapClient = Identity.getSignInClient(applicationContext)
//            )
//        }
//        val viewModel = viewModel<SignViewModel>()
//        val state by viewModel.state.collectAsStateWithLifecycle()
//
//        val launcher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.StartActivityForResult(),
//            onResult = { result ->
//                if (result.resultCode == ComponentActivity.RESULT_OK) {
//                    lifecycleScope.launch {
//                        val signResult = googleAuthUiClient.getSignInWithIntent(
//                            intent = result.data ?: return@launch
//                        )
//                        viewModel.onSignInResult(signResult)
//                    }
//                }
//            }
//        )
////
//        LaunchedEffect(key1 = state.isSuccess) {
//            if (state.isSuccess == true) {
//                // success message
//                Toast.makeText(
//                    applicationContext,
//                    "Success",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//        SignInScreen(
//            state = state,
//            onSignInClick = {
//                lifecycleScope.launch {
//                    val signInIntentSender = googleAuthUiClient.signIn()
//                    launcher.launch(
//                        null
////                                            IntentSenderRequest.Builder(
////                                                intentSender = signInIntentSender ?: return@launch
////                                            ).build()
//                    )
//                }
//            }
//        )
//    }
//}