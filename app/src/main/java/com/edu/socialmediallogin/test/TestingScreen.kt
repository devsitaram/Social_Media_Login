package com.edu.socialmediallogin.test

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.AndroidFont
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.ui.components.ButtonView
import com.edu.socialmediallogin.presentation.ui.components.InputTextFieldView
import com.edu.socialmediallogin.presentation.ui.navigations.ScreenList

@SuppressLint("UnsafeOptInUsageError", "OpaqueUnitKey")
@Composable
fun TestingViewScreen() {

    val videoUrls = "https://videos.mysecondteacher.com.np/videos/6256a4e9280d810012461500/hls/6256a4e9280d810012461500.m3u8?Key-Pair-Id=K2LBSI333XICIR&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9pdnktdmlkZW9sZ3kuY29tL3ZpZGVvcy82MjU2YTRlOTI4MGD8fX0="

    val context = LocalContext.current
    val exoplayer = remember {
        ExoPlayer.Builder(context).build()
    }

    val playerView = remember {
        PlayerView(context).apply {
            hideController()
            useController = true
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            player = exoplayer
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    DisposableEffect(Unit) {
        val defaultDataSourceFactory = DefaultDataSource.Factory(context)
        val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
            context, defaultDataSourceFactory
        )
        val source = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(videoUrls)))
        exoplayer.setMediaSource(source)
        exoplayer.prepare()
        onDispose { exoplayer.release() }
    }

    AndroidView(factory = { playerView })
}