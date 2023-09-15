package com.edu.socialmediallogin.ui.firebase.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.edu.socialmediallogin.presentation.components.TextView
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun SignInScreen(state: SignInState, onSignInClick: () -> Unit) {

    val context = LocalContext.current

    LaunchedEffect(key1 = state.isError){
        state.isError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center){
        Button(onClick = { onSignInClick() }) {
            TextView(text = "Sign In")
        }
    }
}