package com.edu.socialmediallogin.presentation.screen

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.data.common.Constants.DEFAULT_IMAGE_URL
import com.edu.socialmediallogin.data.common.Constants.HTTPS_IMAGE_BASE_URL
import com.edu.socialmediallogin.presentation.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.components.ContentCardView
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.viewmodel.SubjectViewModel

@Composable
fun SubjectViewScreen(
    navController: NavHostController,
    viewModel: SubjectViewModel = hiltViewModel()
) {
    val context = (LocalContext.current as Activity)
    val getSharedPreferences = context.getSharedPreferences("social_media_preferences", Context.MODE_PRIVATE)
    val getAccessToken = getSharedPreferences.getString("accessToken", "")

    val result = viewModel.subjectList.value
    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = result.error)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonAppBar(title = "", navController = navController)
            TextView(
                text = "Subject",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )

            result.data?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    items(it) {
                        val title = it?.name.toString()
                        val descriptions = it?.description.toString()
                        val subjectDesc = descriptions.ifEmpty { "Descriptions is not available." }
                        val imageUrl =
                            if (it?.photoUrl.isNullOrEmpty()) DEFAULT_IMAGE_URL else it?.photoUrl.toString()
                        val videoUrl = "Video Url is not available"
                        val isIvy = it?.isIvy

                        if (getAccessToken.isNullOrEmpty()){
                            val isData = viewModel.insertSubject(imageUrl, title, subjectDesc, isIvy)
                            Toast.makeText(context, isData, Toast.LENGTH_SHORT).show()
                        }

                        ContentCardView(
                            imageUrl = HTTPS_IMAGE_BASE_URL + imageUrl,
                            topic = title,
                            description = descriptions,
                            onClickable = {
                                navController.navigate("VideoScreen/${title}/${subjectDesc}/${videoUrl}")
                            }
                        )
                    }
                }
            }
        }
    }
}

// navController.navigate(route = "VideoScreen/${title}/${videoDesc}/${videoUrl}")