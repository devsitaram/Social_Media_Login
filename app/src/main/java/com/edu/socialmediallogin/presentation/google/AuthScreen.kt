package com.edu.socialmediallogin.presentation.google

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SensorOccupied
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.ProgressIndicator
import com.edu.socialmediallogin.presentation.compose.ScreenList
import com.google.android.gms.common.api.ApiException
import com.squareup.moshi.Moshi

@Composable
fun AuthScreen(navController: NavHostController) {

    val context = LocalContext.current
    val mSignInViewModel: SignInGoogleViewModel = viewModel(factory = SignInGoogleViewModel.SignInGoogleViewModelFactory(context))

    val googleUser = mSignInViewModel.googleUser.observeAsState()

    val isError = rememberSaveable { mutableStateOf(false) }

    val authResultLauncher = rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                mSignInViewModel.fetchSingInUser("np01ma4s22003@islingtoncollege.edu.np", "Sita Ram Thing MAD")
//                val gsa = task?.getResult(ApiException::class.java)
//                if (gsa != null) {
//                    mSignInViewModel.fetchSingInUser(gsa.email, gsa.displayName)
//                } else {
//                    isError.value = true
//                }
            } catch (e: ApiException) {
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }

    AuthView(
        onClick = { authResultLauncher.launch(1) },
        isError = isError.value,
        mSignInViewModel = mSignInViewModel,
    )

    // Strange issue after upgrading to latest version
//    if (mSignInViewModel.googleUser.value != null) {
//        LaunchedEffect(key1 = Unit) {
//            mSignInViewModel.hideLoading()
//            navController.navigate(ScreenList.LoginScreen.route) {
//                popUpTo(ScreenList.AuthScreen.route) {
//                    inclusive = true
//                }
//            }
//        }
//    }

    googleUser?.let {
        mSignInViewModel.hideLoading()
        Toast.makeText(context, "-> $googleUser", Toast.LENGTH_SHORT).show()
        if (googleUser.value != null){
            mSignInViewModel.hideLoading()
            navController.navigate(ScreenList.LoginScreen.route) {
                popUpTo(ScreenList.AuthScreen.route) {
                    inclusive = true
                }
            }
        }
//        val moshi = Moshi.Builder().build()
//        val jsonAdapter = moshi.adapter(GoogleUserModel::class.java)
//        val userJson = jsonAdapter.toJson(googleUser.value)
//        navController.navigate(ScreenList.HomeScreen.replace("{user}", userJson))
//        navController.navigate(ScreenList.RegisterScreen.route)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AuthView(onClick: () -> Unit, isError: Boolean = false, mSignInViewModel: SignInGoogleViewModel) {
    val state = mSignInViewModel.loading.observeAsState()
    val isLoading = state.value
    Scaffold {
        if (isLoading == true && !isError) {
            ProgressIndicator()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.weight(1F))
                Spacer(modifier = Modifier.weight(1F))
                SignInGoogleButton(
                    onClick = {
                        mSignInViewModel.showLoading()
                        onClick()
                    }
                )
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    text = "Google",
                    textAlign = TextAlign.Center,
                )

                when {
                    isError -> {
                        isError.let {
                            Text(
                                "Cannot be Login",
                                style = MaterialTheme.typography.h6,
                                color = MaterialTheme.colors.error
                            )
                            mSignInViewModel.hideLoading()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SignInGoogleButton(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.clickable(
            onClick = onClick
        ),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.SensorOccupied,
                contentDescription = "Google Login", modifier = Modifier
                    .height(20.dp)
                    .width(20.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign in With Google"
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}