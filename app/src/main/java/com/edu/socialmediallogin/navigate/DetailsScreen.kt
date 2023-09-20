package com.edu.socialmediallogin.navigate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edu.socialmediallogin.presentation.components.TextView

@Composable
fun DetailsScreen(name: String?, age: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextView(text = "Details Screen")
        TextView(text = "Name: $name")
        TextView(text = "Age: $age")
    }
}