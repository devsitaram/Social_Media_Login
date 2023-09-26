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

//    val videoUrls = "https://videos.mysecondteacher.com.np/videos/6256a4e9280d810012461500/hls/6256a4e9280d810012461500.m3u8?Key-Pair-Id=K2LBSI333XICIR&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9pdnktdmlkZW9zLmFkdmFuY2VkcGVkYWdvZ3kuY29tL3ZpZGVvcy82MjU2YTRlOTI4MGQ4MTAwMTI0NjE1MDAvaGxzLyoiLCJDb25kaXRpb24iOnsiRGF0ZUxlc3NUaGFuIjp7IkFXUzpFcG9jaFRpbWUiOjE2OTU3MzA5ODV9fX1dfQ__&Signature=aRVnfONWuvgheCOtwU4Eld1U-8PaVT3ImiX0b~oU10iW4fMGnLUlDvfTcCExh2Nj8E1PNAby~wvdO0wNggWLzUaJMYNMXO3R2J8EwMkvqNX9b6nb8rndBTlk6Xt~IfVa1NMtNXQGZHpFz9SdZdjekvZM1HHABtLPdcsB7NunFgEGejBUTlTDPySBpbxb4sGeN0vQvtX1tWBXTUyOYn631rGz0TfsoYS4aXmRwh7YLBVL3V0lo92GO7LGUBPYn~kQ3uRqUeRC3Q0rS723rnwBttwhwsn~iYGtC058tCEFjuaidWLLBLmFqivjghTUzKmZC73-ws9QBMGM3fgiXXn7Tw__&Signed-Query-String=S2V5LVBhaXItSWQ9SzJMQlNJMzMzWElDSVImUG9saWN5PWV5SlRkR0YwWlcxbGJuUWlPbHQ3SWxKbGMyOTFjbU5sSWpvaWFIUjBjSE02THk5cGRua3RkbWxrWlc5ekxtRmtkbUZ1WTJWa2NHVmtZV2R2WjNrdVkyOXRMM1pwWkdWdmN5ODJNalUyWVRSbE9USTRNR1E0TVRBd01USTBOakUxTURBdmFHeHpMeW9pTENKRGIyNWthWFJwYjI0aU9uc2lSR0YwWlV4bGMzTlVhR0Z1SWpwN0lrRlhVenBGY0c5amFGUnBiV1VpT2pFMk9UVTNNekE1T0RWOWZYMWRmUV9fJlNpZ25hdHVyZT1hUlZuZk9OV3V2Z2hlQ090d1U0RWxkMVUtOFBhVlQzSW1pWDBifm9VMTBpVzRmTUduTFVsRHZmVGNDRXhoMk5qOEUxUE5BYnl+d3ZkTzB3TmdnV0x6VWFKTVlOTVhPM1IySjhFd01rdnFOWDliNm5iOHJuZEJUbGs2WHR+SWZWYTFOTXROWFFHWkhwRno5U2RaZGpla3ZaTTFISEFCdExQZGNzQjdOdW5GZ0VHZWpCVVRsVERQeVNCcGJ4YjRzR2VOMHZRdnRYMXRXQlhUVXlPWW42MzFyR3owVGZzb1lTNGFYbVJ3aDdZTEJWTDNWMGxvOTJHTzdMR1VCUFlufmtRM3VScVVlUkMzUTByUzcyM3Jud0J0dHdod3NufmlZR3RDMDU4dENFRmp1YWlkV0xMQkxtRnFpdmpnaFRVekttWkM3My13czlRQk1HTTNmZ2lYWG43VHdfXw=="
//    val context = LocalContext.current
//    val exoplayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val defaultDataSourceFactory = DefaultDataSource.Factory(context)
//            val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
//                context, defaultDataSourceFactory
//            )
//
//            val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
//                MediaItem.fromUri(Uri.parse(videoUrls))
//            )
//
//            setMediaSource(source)
//            prepare()
//        }
//    }
//
//    DisposableEffect(
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    hideController()
//                    useController = true
//                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//
//                    player = exoplayer
//                    layoutParams = FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
//                    )
//                }
//            }
//        )
//    ) {
//        onDispose { exoplayer.release() }
//    }

    val videoUrls =
        "https://videos.mysecondteacher.com.np/videos/6256a4e9280d810012461500/hls/6256a4e9280d810012461500.m3u8?Key-Pair-Id=K2LBSI333XICIR&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9pdnktdmlkZW9sZ3kuY29tL3ZpZGVvcy82MjU2YTRlOTI4MGD8fX0="

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

        // Set up event listener for error handling
//        exoplayer.addListener(object : Player.Listener {
//            override fun onPlayerError(error: PlaybackException) {
//                val errorMessage = when (error.type) {
//                    PlaybackException.TYPE_SOURCE -> "Source error: ${error.sourceException?.message}"
//                    PlaybackException.TYPE_RENDERER -> "Renderer error: ${error.rendererException?.message}"
//                    PlaybackException.TYPE_UNEXPECTED -> "Unexpected error: ${error.unexpectedException?.message}"
//                    else -> "Unknown error"
//                }
//                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//        })

        exoplayer.prepare()
        onDispose { exoplayer.release() }
    }

    AndroidView(factory = { playerView })
}