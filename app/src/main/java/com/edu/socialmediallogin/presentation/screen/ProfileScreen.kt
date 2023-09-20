package com.edu.socialmediallogin.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.ProgressIndicator
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileViewScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val profileResult = profileViewModel.profileState.value

    if (profileResult.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ProgressIndicator()
        }
    }

    if (profileResult.isError.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = profileResult.isError)
        }
    }

    profileResult.data?.result.let {
        val profiles = profileResult.data?.result
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TextView(text = profiles?.fullName.toString())

                TextView(text = profiles?.emailAddress.toString())

                TextView(text = profiles?.photoUrl.toString())

                TextView(text = profiles?.location.toString())

                TextView(text = profiles?.phoneNumber.toString())
            }
        }
    }
}