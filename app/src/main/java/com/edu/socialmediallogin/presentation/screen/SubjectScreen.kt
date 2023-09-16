package com.edu.socialmediallogin.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.ContentCardView
import com.edu.socialmediallogin.presentation.components.TextView
import com.edu.socialmediallogin.presentation.compose.ScreenList
import com.edu.socialmediallogin.presentation.viewmodel.SubjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectViewScreen(
    navController: NavHostController,
    viewModel: SubjectViewModel = hiltViewModel()
) {

    val result = viewModel.imageList.value

    var queryText by remember { mutableStateOf("") }

    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TextView(text = result.error)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = queryText,
                onValueChange = {
                    queryText = it
                    viewModel.updateQuery(query = queryText)
                },
                placeholder = { Text(text = "Search Flower") },
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
            Divider()
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
                        ContentCardView(
                            imageUrl = it.imageUrl,
                            topic = it.title,
                            description = it.urlDescriptions,
                            onClickable = {
                                // data transfer and navigate
//                                    val videoDetails = VideoDetails(
//                                        title = item.title, descriptions = item.description,
//                                        videoUri = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
//                                    )
//                                    videoViewModel.addVideoDetails(newVideoDetails = videoDetails)
                                    navController.navigate(ScreenList.VideoScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}