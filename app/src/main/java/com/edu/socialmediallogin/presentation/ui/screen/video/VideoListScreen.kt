package com.edu.socialmediallogin.presentation.ui.screen.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.edu.socialmediallogin.data.common.Constants
import com.edu.socialmediallogin.data.source.local.VideoEntity
import com.edu.socialmediallogin.presentation.ui.components.AsyncImageView
import com.edu.socialmediallogin.presentation.ui.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.ui.components.TextView
import com.edu.socialmediallogin.presentation.viewmodel.VideoViewModel

@Composable
fun VideoListViewScreen(
    navController: NavHostController,
    videoViewModel: VideoViewModel = hiltViewModel()
) {

    val videos = videoViewModel.videoList.value

    videos.isData?.let { videoResult ->
        val id = videoResult.id
        val completion = videoResult.completion
        val photoUrl = videoResult.photoUrl
        val chapters = videoResult.chapters
        val subjectDescription = videoResult.subjectDescription
        val totalVideoWatchedTimeInSeconds = videoResult.totalVideoWatchedTimeInSeconds
        val className = videoResult.className
        val subjectName = videoResult.subjectName
        val mastery = videoResult.mastery
        LaunchedEffect(key1 = id, block = {
            val listOfVideo = listOf(
                VideoEntity(
                    id = id,
                    completion = completion,
                    photoUrl = photoUrl,
                    chapters = chapters,
                    subjectDescription = subjectDescription,
                    totalVideoWatchedTimeInSeconds = totalVideoWatchedTimeInSeconds,
                    className = className,
                    subjectName = subjectName,
                    mastery = mastery
                )
            )
//            videoViewModel.insertVideo(listOfVideo = listOfVideo)
        })
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonAppBar(title = "", navController = navController)
                TextView(
                    text = "Videos",
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    items(videoResult.chapters.orEmpty()) { chapterItem ->
                        chapterItem?.topics.orEmpty().forEach { topicItem ->
                            topicItem?.videos.orEmpty().forEach { videoItem ->
                                val videoId = videoItem?.videoId ?: "is empty"
                                val videoTitle = videoItem?.videoTitle ?: "is empty"
                                val thumbNailUrl = videoItem?.thumbNailUrl ?: "is empty"
                                val videoDuration = videoItem?.videoDuration ?: "is empty"
                                // Now you can use these properties in your UI
                                VideoCardView(
                                    imageUrl = Constants.HTTPS_IMAGE_BASE_URL + thumbNailUrl,
                                    videoTitle = videoTitle,
                                    videoDuration = videoDuration,
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VideoCardView(
    imageUrl: String,
    videoTitle: String,
    videoDuration: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                bottom = 20.dp
            )
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp), contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImageView(
                model = imageUrl,
                modifier = Modifier
            )
//            VectorIconView(imageVector = Icons.Default.PlayCircleOutline, tint = Color.White)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextView(
                    text = "10:41",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(5.dp)
                )
            }
        }

        TextView(
            text = videoTitle,//"Introduction to Grade 11 Compulsory English is Compulsory English",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                color = Color.DarkGray
            ),
            modifier = Modifier.padding(5.dp)
        )
    }
}