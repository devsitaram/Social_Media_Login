package com.edu.socialmediallogin.navigate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.TextView

@Composable
fun WelcomeScreen(navController: NavHostController) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextView(text = "Welcome Screen")
        Spacer(modifier = Modifier.height(height = 8.dp))
        // name text field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { TextView(text = "Name") },
            placeholder = { TextView(text = "Enter your name") }
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        // age text field
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { TextView(text = "Age") },
            placeholder = { TextView(text = "Enter your age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(height = 8.dp))

        // submit button
        Button(
            onClick = {
                navController.navigate(route = "details_screen/$name/$age")
            }
        ) {
            TextView(text = "Submit")
        }
    }
}