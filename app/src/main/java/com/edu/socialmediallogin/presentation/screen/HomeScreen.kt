package com.edu.socialmediallogin.presentation.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources.Theme
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.components.ButtonView
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.compose.ScreenList
import com.edu.socialmediallogin.ui.theme.SocialMedialLoginTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeViewScreen(
    navController: NavHostController,
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonAppBar(title = "Home Page", navController = navController)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Switch(checked = checked, onCheckedChange = { onCheckedChange() })
                TextView(text = if (checked) "Dark Mode" else "Light Mode")
            }
        }
    }
}