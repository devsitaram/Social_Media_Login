package com.edu.socialmediallogin.presentation.ui.screen.video

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.socialmediallogin.presentation.ui.components.TextView
import com.edu.socialmediallogin.presentation.viewmodel.video.VideoIvyViewModel
import com.edu.socialmediallogin.presentation.viewmodel.video.VideoViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ExoPlayer

@Composable
fun VideoViewScreen(
    videoId: String?,
    videoViewModel: VideoViewModel = hiltViewModel(),
    videoIvyViewModel: VideoIvyViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val videoEmbedToken = videoViewModel.videoEmbedToken.value
    val videoStreamingUrl = videoIvyViewModel.videoStreamingUrl.value

    if (videoEmbedToken.isLoading || videoStreamingUrl.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (videoEmbedToken.isError.isNotBlank() || videoStreamingUrl.isError.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = videoEmbedToken.isError)
        }
    }

    if (videoEmbedToken.isError.isNotBlank() || videoStreamingUrl.isError.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = videoEmbedToken.isError)
        }
    }
    // get video embed token
    LaunchedEffect(key1 = videoId, block = {
        videoViewModel.getVideoEmbedToken(videoId)
    })

    videoEmbedToken.isData?.let { token ->
        val embedToken = token.embedToken.toString()
        // get video streamingUrl
        LaunchedEffect(key1 = embedToken, block = {
            // get embed token
            videoIvyViewModel.getStreamingUrl(embedToken)
        })

        videoStreamingUrl.isData?.let { url ->

            val videoUrls = url.streamingUrl.toString()

            val exoPlayer = ExoPlayer.Builder(context).build()
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrls))
            exoPlayer.setMediaItem(mediaItem)

            val playerView = StyledPlayerView(context)
            playerView.player = exoPlayer

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                DisposableEffect(
                    AndroidView(factory = { playerView })
                ) {
                    exoPlayer.prepare()
                    exoPlayer.playWhenReady = true

                    onDispose {
                        exoPlayer.release()
                    }
                }
            }


//            val videoView = VideoView(context)
//            videoView.setVideoURI(Uri.parse(videoUrls))
//            videoView.start()
//            AndroidView(
//                factory = { videoView },
//                modifier = Modifier.fillMaxSize(),
//            )
        }
    }
}