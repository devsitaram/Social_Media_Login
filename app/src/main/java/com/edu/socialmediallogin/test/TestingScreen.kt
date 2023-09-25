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

    val videoUrls = "https://embed-ivy.advancedpedagogy.com/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTU2NzE1NjIsInBhcmFtcyI6eyJvd25lciI6IjYwMTBlNWExMTJiYmI0MDAxMjE0YmQ5NyIsInZpZXdlciI6Ijk5MTA2IiwidmlkZW9JZCI6IjYyNTdhNDIyOTUyYjM2MDAxMjk2MGI0MSIsInBsYXliYWNrSWQiOiJHVlhGU21HVW9mMDEzYjAwSklLejAyS0pxMVNqeHppMWRBMWVOR0VvalBVVUtNIiwicGxheWJhY2tSYXRlIjowLCJjYW5TZWVrIjp0cnVlLCJpc1dvcmtlZE91dCI6ZmFsc2UsInRpbWUiOjExNDMuOTk5LCJwYXJlbnRUaGVtZSI6Imdsb2JhbCJ9LCJpYXQiOjE2OTU2MjgzNjJ9.wer5RR-ypQ2XqPqSI9EA_j9H_EoYMjpwK1dsySx1ZnM"
    val context = LocalContext.current
    val exoplayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val defaultDataSourceFactory = DefaultDataSource.Factory(context)
            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                context, defaultDataSourceFactory
            )

            val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                MediaItem.fromUri(Uri.parse(videoUrls))
            )

            setMediaSource(source)
            prepare()
        }
    }

    DisposableEffect(
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    player = exoplayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                }
            }
        )
    ) {
        onDispose { exoplayer.release() }
    }
}