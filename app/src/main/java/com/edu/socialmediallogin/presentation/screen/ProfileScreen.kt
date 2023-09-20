package com.edu.socialmediallogin.presentation.screen

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.R
import com.edu.socialmediallogin.presentation.components.ProgressIndicator
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.viewmodel.ProfileViewModel
import com.edu.socialmediallogin.ui.theme.green
import com.edu.socialmediallogin.ui.theme.skyBlue
import com.edu.socialmediallogin.ui.theme.softWhite
import com.edu.socialmediallogin.ui.theme.white

@Composable
fun ProfileViewScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileResult = profileViewModel.profileState.value
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences("social_media_preferences", Context.MODE_PRIVATE)
    val activity = navController.context as? ComponentActivity

    var showDialogBox by remember { mutableStateOf(false) }

    if (profileResult.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            ProgressIndicator()
        }
    }

    if (profileResult.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = profileResult.isError)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        profileResult.data?.let {
            val profiles = profileResult.data.result
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                UserProfileView(
                    userName = profiles?.fullName.toString(),
                    emailAddress = profiles?.emailAddress.toString()
                )
                CollegeAddressView(
                    collegeName = profiles?.schoolName.toString(),
                    location = profiles?.location.toString()
                )
                ListOfMoreItems(
                    icon = Icons.Default.Logout, title = "Logout",
                    onClick = {
                        showDialogBox = true
                    }
                )
            }
        }
    }

    if (showDialogBox) {
        ConfirmationDialogBox(
            title = "Log out?",
            text = "Are you sure you want to log out?",
            onDismiss = { showDialogBox = false },
            onConfirm = {
                // remove access_token
                val editor = sharedPreferences.edit()
                editor.putString("accessToken", "").apply()
                activity?.finish() // close the app
            }
        )
    }
}

@Composable
fun UserProfileView(userName: String, emailAddress: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(modifier = Modifier, shape = CircleShape) {
            Icon(
                imageVector = Icons.Default.PersonOutline,
                contentDescription = null,
                tint = green,
                modifier = Modifier
                    .size(60.dp)
                    .background(green.copy(alpha = 0.20f))
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(220.dp)
        ) {
            TextView(
                text = userName,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
            )
            TextView(
                text = emailAddress,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
            )
        }
        Card(
            modifier = Modifier,
            shape = CircleShape, // Use CircleShape for circular border
            border = BorderStroke(1.dp, skyBlue), // BorderStroke with desired color and width
        ) {
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.size(25.dp), // Increased size for the IconButton
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = skyBlue,
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}

@Composable
fun CollegeAddressView(collegeName: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(softWhite)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.mipmap.img_islington_college),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                TextView(
                    text = collegeName,
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                )
                TextView(
                    text = location,
                    style = TextStyle(
                        fontSize = 13.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Composable
fun ListOfMoreItems(icon: ImageVector, title: String, onClick: () -> Unit) {
    Column(modifier = Modifier.background(white)) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.DarkGray
            )
            TextView(
                text = title,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                ),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun ConfirmationDialogBox(
    title: String,
    text: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
//    val activity = (LocalContext.current as Activity)
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { TextView(text = title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold) },
        text = { TextView(text = text, color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                TextView(text = "No", color = Color.Blue)
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() } // activity.finish()
            ) {
                TextView(text = "Yes", color = Color.Blue)
            }
        }
    )
}