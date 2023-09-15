package com.edu.socialmediallogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edu.socialmediallogin.ui.firebase.presentation.GoogleAuthUiClient
import com.edu.socialmediallogin.ui.firebase.presentation.SignInScreen
import com.edu.socialmediallogin.ui.firebase.presentation.viewmodel.SignViewModel
import com.edu.socialmediallogin.presentation.compose.NavigationViewScreen
import com.edu.socialmediallogin.ui.theme.SocialMedialLoginTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// https://www.youtube.com/watch?v=gIuHATUBGvA
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create the Shared Preferences
//        val getSharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)
//        val getUserDevice = getSharedPreferences.getString("login_screen", "")

        setContent {
            SocialMedialLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationViewScreen(navController = navController)
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