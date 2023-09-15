package com.edu.socialmediallogin.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.presentation.components.ButtonView
import com.edu.socialmediallogin.presentation.components.ClickableTextView
import com.edu.socialmediallogin.presentation.components.InputTextFieldView
import com.edu.socialmediallogin.presentation.components.PasswordTextFieldView
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.compose.ScreenList

@Composable
fun SignUpScreenViewScreen(navController: NavHostController) {

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isEmailEmpty by remember { mutableStateOf(false) }
    var isNameEmpty by remember { mutableStateOf(false) }
    var isPasswordEmpty by remember { mutableStateOf(false) }

    var isError by remember { mutableStateOf(false) }

    // on click function
    val onRegisterClick: () -> Unit = {
        isEmailEmpty = email.isEmpty()
        isNameEmpty = name.isEmpty()
        isPasswordEmpty = password.isEmpty()
        if (email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
            isError = true
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
        }
    }

    // sign screen page
    Surface(modifier = Modifier.fillMaxSize()) {
        // child layout file
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
            TextView(
                text = "Sing Up",
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
            Image(
                painter = painterResource(id = R.mipmap.ic_login), contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(5.dp)
            )
            TextView(
                text = "Create an Account",
                color = Color.Black,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 5.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal
                ),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            if (isError) {
                TextView(
                    text = "Enter the valid details!",
                    style = TextStyle(
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.Center) {
                // email
                InputTextFieldView(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    placeholder = "Email",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    textStyle = TextStyle(fontSize = 12.sp),
                    isEmpty = isEmailEmpty,
                    isError = isError,
                    errorColor = Color.Red,
                    errorMessage = "The email is empty!",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                // username
                InputTextFieldView(
                    value = name,
                    onValueChange = { name = it },
                    label = "Username",
                    placeholder = "Username",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.PersonOutline,
                            contentDescription = null
                        )
                    },
                    textStyle = TextStyle(fontSize = 12.sp),
                    isEmpty = isNameEmpty,
                    isError = isError,
                    errorColor = Color.Red,
                    errorMessage = "The username is empty!",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                // password
                PasswordTextFieldView(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    placeholder = "Password",
                    textStyle = TextStyle(fontSize = 12.sp),
                    isEmpty = isPasswordEmpty,
                    isError = isError,
                    errorColor = Color.Red,
                    errorMessage = "The password is empty!",
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(30.dp))
                // button
                ButtonView(
                    onClick = { onRegisterClick() },
                    btnColor = Color.Blue,
                    text = "Sign Up",
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                TextView(
                    text = "By registering, you confirm that your accept our team of Use and Privacy policy.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth()) // using the divider
            // register text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextView(
                    text = "If you have an already account?",
                    color = Color.Black,
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
                ClickableTextView(
                    text = "Sing In",
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                    ),
                    overflow = TextOverflow.Clip,
                    onTextLayout = {},
                    onClick = { navController.navigate(ScreenList.LoginScreen.route) },
                )
            }
        }
    }
}