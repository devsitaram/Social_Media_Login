package com.edu.socialmediallogin.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.ui.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.ui.components.TextView

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeViewScreen(
    navController: NavHostController,
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 50.dp),
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