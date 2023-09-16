package com.edu.socialmediallogin.presentation.screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.presentation.components.ButtonView
import com.edu.socialmediallogin.presentation.components.CheckboxComponent
import com.edu.socialmediallogin.presentation.components.ClickableTextView
import com.edu.socialmediallogin.presentation.components.InputTextFieldView
import com.edu.socialmediallogin.presentation.components.PasswordTextFieldView
import com.edu.socialmediallogin.presentation.components.ProgressIndicator
import com.edu.socialmediallogin.presentation.components.RoundedCornerCardView
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.compose.ScreenList
import com.edu.socialmediallogin.presentation.google.GoogleApiContract
import com.edu.socialmediallogin.presentation.google.SignInGoogleViewModel
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInViewScreen(navController: NavHostController) {
    val context = LocalContext.current

    // google account to login
    val mSignInViewModel: SignInGoogleViewModel = viewModel(factory = SignInGoogleViewModel.SignInGoogleViewModelFactory(context))
    val googleUser = mSignInViewModel.googleUser.observeAsState()
    val isLoading = mSignInViewModel.loading.observeAsState()
    val authResultLauncher = rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                mSignInViewModel.fetchSingInUser(
                    email = "np01ma4s22003@islingtoncollege.edu.np",
                    name = "Sita Ram Thing MAD"
                )
//                val gsa = task?.getResult(ApiException::class.java)
//                if (gsa != null) {
//                    mSignInViewModel.fetchSingInUser(gsa.email, gsa.displayName)
//                } else {
//                    Toast.makeText(context, "Invalid user", Toast.LENGTH_SHORT).show()
////                    isGoogleError.value = true
//                }
            } catch (e: ApiException) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    //local
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailEmpty by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    // Login button click handler
    val onLoginClick: () -> Unit = {
        isEmailEmpty = email.isEmpty()
        isPasswordEmpty = password.isEmpty()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            isError = true
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
//            signInViewModel.loginUser(email, password)
//            loginViewModel.getUserLoginDataStatus(email, password)
//            val loginViewModel = LoginViewModel()
//            val isValidLogin = loginViewModel.loginDetails(username, password, context)
//            if (isValidLogin) {
//                // Navigate to the home screen
//                navController.navigate(User.Main.route) {
//                    // callback old screen
//                    popUpTo(User.Login.route) {
//                        inclusive = true // close the previous screen
//                    }
//                }
//            }
//        }
    }

    if (isLoading.value == true) {
        ProgressIndicator()
    } else {
        Surface(Modifier.fillMaxSize()) {
            // child layout file
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 15.dp)
                ) {
                    TextView(
                        text = "Sing In",
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(horizontal = 5.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                Image(
                    painter = painterResource(id = R.mipmap.ic_login), contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                TextView(
                    text = "Login Your Details",
                    color = colorResource(id = R.color.black),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                if (isError) {
                    TextView(
                        text = "Enter the valid details!",
                        style = TextStyle(
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(Color.White)
                ) {
                    // username
                    InputTextFieldView(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        placeholder = "Email",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonOutline,
                                contentDescription = null
                            )
                        },
                        textStyle = TextStyle(fontSize = 12.sp),
                        isEmpty = isEmailEmpty,
                        isError = isError,
                        errorMessage = "The email is empty!",
                        errorColor = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    // password
                    PasswordTextFieldView(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        placeholder = "Password",
                        textStyle = TextStyle(fontSize = 12.sp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        isEmpty = isPasswordEmpty,
                        isError = isError,
                        errorColor = Color.Red,
                        errorMessage = "The password is empty!",
                        modifier = Modifier.fillMaxWidth()
                    )

                    // checkbox
                    CheckboxComponent(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                    )
                    // login button
                    ButtonView(
                        onClick = { onLoginClick() },
                        btnColor = Color.Blue,
                        text = "Login",
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ClickableTextView(
                            text = "Forgot password?",
                            style = TextStyle(textAlign = TextAlign.Start),
                            overflow = TextOverflow.Clip,
                            onTextLayout = {},
                            onClick = { /**navController.navigate(User.ForgotPassword.route)*/ },
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(top = 25.dp))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(top = 15.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextView(
                                text = "Register your",
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontStyle = FontStyle.Normal
                            )
                            Spacer(modifier = Modifier.padding(1.dp))
                            ClickableTextView(
                                text = "account?",
                                style = TextStyle(
                                    color = Color.Blue,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontStyle = FontStyle.Normal
                                ),
                                overflow = TextOverflow.Clip,
                                onTextLayout = {},
                                onClick = { navController.navigate(ScreenList.RegisterScreen.route) }
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextView(
                        text = "Or login with social media",
                        modifier = Modifier
                    )
                    Row(
                        modifier = Modifier
                            .width(150.dp)
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RoundedCornerCardView(
                            modifier = Modifier.size(30.dp),
                            shape = RoundedCornerShape(30.dp),
                            painter = painterResource(id = R.mipmap.ic_google),
                            onClick = {
                                mSignInViewModel.showLoading()
                                authResultLauncher.launch(1)
                            }
                        )
                        RoundedCornerCardView(
                            modifier = Modifier.size(30.dp),
                            shape = RoundedCornerShape(30.dp),
                            painter = painterResource(id = R.mipmap.img_facebook),
                            onClick = {}
                        )
                        RoundedCornerCardView(
                            modifier = Modifier.size(30.dp),
                            shape = RoundedCornerShape(30.dp),
                            painter = painterResource(id = R.mipmap.img_twiter),
                            onClick = {}
                        )
                    }
                }
            }
        }
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
        googleUser.let {
            mSignInViewModel.hideLoading()
            if (googleUser.value != null) {
                mSignInViewModel.hideLoading()
                navController.navigate(ScreenList.HomeScreen.route) {
                    popUpTo(ScreenList.LoginScreen.route) {
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
}