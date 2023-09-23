package com.edu.socialmediallogin.presentation.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.data.common.util.Validators.emailValidation
import com.edu.socialmediallogin.presentation.ui.components.ButtonView
import com.edu.socialmediallogin.presentation.ui.components.CheckboxComponent
import com.edu.socialmediallogin.presentation.ui.components.ClickableTextView
import com.edu.socialmediallogin.presentation.ui.components.MessageDialogBox
import com.edu.socialmediallogin.presentation.ui.components.InputTextFieldView
import com.edu.socialmediallogin.presentation.ui.components.PainterImageView
import com.edu.socialmediallogin.presentation.ui.components.PasswordTextFieldView
import com.edu.socialmediallogin.presentation.ui.components.ProgressIndicator
import com.edu.socialmediallogin.presentation.ui.components.RoundedCornerCardView
import com.edu.socialmediallogin.presentation.ui.components.TextView
import com.edu.socialmediallogin.presentation.ui.navigations.Screen
import com.edu.socialmediallogin.ui.others.google.GoogleApiContract
import com.edu.socialmediallogin.presentation.viewmodel.signin.GoogleSignInViewModel
import com.edu.socialmediallogin.presentation.viewmodel.signin.SignInViewModel
import com.google.android.gms.common.api.ApiException

@Composable
fun SignInViewScreen(
    navController: NavHostController,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("social_media_preferences", Context.MODE_PRIVATE)
    val userLoginResult = signInViewModel.signInState.value

    // google account to login
//    val authViewModel: AuthViewModel = hiltViewModel()
//    val googleLoginResult = authViewModel.googleLoginResult.observeAsState()
    val googleSignInViewModel: GoogleSignInViewModel =
        viewModel(factory = GoogleSignInViewModel.SignInGoogleViewModelFactory(context))
    val googleUser = googleSignInViewModel.googleUser.observeAsState()
    val isLoading = googleSignInViewModel.loading.observeAsState()
    val isGoogleSignInError = rememberSaveable { mutableStateOf(false) }
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                googleSignInViewModel.fetchSingInUser(
                    email = "np01ma4s22003@islingtoncollege.edu.np",
                    name = "Sita Ram Thing MAD"
                )
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
                    googleSignInViewModel.fetchSingInUser(gsa.email, gsa.displayName)
                } else {
                    Toast.makeText(context, "Invalid user", Toast.LENGTH_SHORT).show()
                    isGoogleSignInError.value = true
                }
//                Log.e("gsa.email", "gsa.email: ${gsa?.email}")
            } catch (e: ApiException) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    // variable
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailEmpty by remember { mutableStateOf(false) }
    var isInvalidEmail by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    // Login button click handler
    val onClickNavigate: () -> Unit = {
        if (emailValidation(email) && password.isNotEmpty()) {
            signInViewModel.getLoginUserAuth(email, password)
            if (userLoginResult.data?.success == true) {
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                        val editor = sharedPreferences.edit()
                        editor.putString("accessToken", "${userLoginResult.data.result?.accessToken}").apply()
                        Log.e("Access Token", "accessToken: ${userLoginResult.data.result?.accessToken}")
                    }
                }
            } else {
                isError = true
            }
        }
    }

    if (isLoading.value == true || userLoginResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ProgressIndicator(modifier = Modifier.wrapContentSize().align(Alignment.Center))
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                PainterImageView(
                    painter = painterResource(id = R.mipmap.ic_login),
                    contentDescription = null,
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
                                contentDescription = null,
                                tint = if (isEmailEmpty || isInvalidEmail) Color.Red else Color.Black
                            )
                        },
                        textStyle = TextStyle(fontSize = 12.sp),
                        isEmpty = isEmailEmpty,
                        isInvalidError = isInvalidEmail,
                        invalidMessage = "Enter valid email address",
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
                                contentDescription = null,
                                tint = if (isPasswordEmpty || isError) Color.Red else Color.Black
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
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.DarkGray,
                            uncheckedColor = Color.Gray
                        ),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                    )
                    // login button
                    ButtonView(
                        onClick = {
                            isEmailEmpty = email.isEmpty()
                            if (!isEmailEmpty) {
                                isInvalidEmail = !emailValidation(email)
                            }
                            isPasswordEmpty = password.isEmpty()
                            onClickNavigate() // navigate
                        },
                        btnColor = ButtonDefaults.buttonColors(Color.Blue),
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
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /**navController.navigate(User.ForgotPassword.route)*/
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
                                onClick = { navController.navigate(Screen.RegisterScreen.route) }
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
                                googleSignInViewModel.showLoading()
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
                    if (isGoogleSignInError.value) {
                        TextView(text = "Invalid login with google", color = Color.Red)
                    }
                }
            }
        }

        googleUser.let {
            googleSignInViewModel.hideLoading()
            if (googleUser.value != null) {
                googleSignInViewModel.hideLoading()
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    if (isError) {
        MessageDialogBox(
            title = "Error",
            descriptions = "Your username or password is invalid. Please try to again or click on Forgot Your Password? below.\n-> ${userLoginResult.isError}",
            onDismiss = {
                isError = false
            },
            btnText = "Okay",
            color = Color.Red
        )
    }
}