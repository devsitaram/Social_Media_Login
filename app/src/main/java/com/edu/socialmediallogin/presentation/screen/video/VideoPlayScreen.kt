@file:Suppress("UNUSED_EXPRESSION")

package com.edu.socialmediallogin.presentation.screen.video

import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.edu.socialmediallogin.presentation.components.TextView
import kotlinx.coroutines.delay

@Composable
fun VideoPlayViewScreen(
    title: String?,
    description: String?,
    videoUrls: String?,
) {

//    val videoPlayViewModel: VideoPlayViewModel = viewModel()
//    val result = videoPlayViewModel.videoDetails

//    val title = result?.title
//    val description = result?.descriptions
//    val videoUrls = result?.videoUri
//        "https://assets.mysecondteacher.com/ap-websites/wp-content/uploads/2023/03/I-want-my-school-future-ready-1.mp4"
//        "https://assets.mysecondteacher.com/ap-websites/wp-content/uploads/2023/03/Do-you-miss-your-school.mp4

    val context = LocalContext.current
    val videoUrl =
        "https://assets.mysecondteacher.com/ap-websites/wp-content/uploads/2023/03/How-do-you-create-assessment-materials-for-your-students.mp4"

    // Create a single ExoPlayer instance for video playback
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build().apply {
                setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
                this.prepare()
                this.playWhenReady = true
            }
    }

    var isPlaying by remember { mutableStateOf(false) }

    var videoTimer by remember { mutableLongStateOf(0L) }

    var totalDuration by remember { mutableLongStateOf(0L) }

    var bufferedPercentage by remember { mutableIntStateOf(0) }

    var playbackState by remember { mutableIntStateOf(0) }

    DisposableEffect(key1 = true) {
        val listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                isPlaying = player.isPlaying
                totalDuration = player.duration.coerceAtLeast(0L)
                videoTimer = player.contentPosition.coerceAtLeast(0L)
                bufferedPercentage = player.bufferedPercentage
                playbackState = player.playbackState
            }

//            override fun onIsPlayingChanged(isPlayingValue: Boolean) {
////                isPlaying = isPlayingValue
//            }
//
//            override fun onPlaybackStateChanged(state: Int) {
////                playbackState = state
//            }
//
//            @SuppressLint("UnsafeOptInUsageError")
//            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
////                indicatorRun = playWhenReady
////                state = playbackState
//            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    // Use a LaunchedEffect to observe the exoPlayer and update isPlaying
    LaunchedEffect(exoPlayer) {
        while (true) {
            val isPlayerPlaying = exoPlayer.isPlaying
            if (isPlaying != isPlayerPlaying) {
                isPlaying = isPlayerPlaying
            }
            delay(1000) // Adjust the delay as needed
        }
    }

    var iconVisible by remember { mutableStateOf(false) }
    LaunchedEffect(iconVisible) {
        if (iconVisible) {
            delay(4000) // Delay for 5 seconds
            iconVisible = false // Hide the icon after the delay
        }
    }

    val handler = remember { Handler(Looper.getMainLooper()) }
    val runnable = remember {
        object : Runnable {
            override fun run() {
                videoTimer = exoPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        }
    }

    DisposableEffect(key1 = Unit) {
        handler.postDelayed(runnable, 1000)
        onDispose {
            handler.removeCallbacks(runnable)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier//.height(275.dp)
                .fillMaxWidth()
                .height(275.dp)
                .background(Color.Black),
        ) {
            if (playbackState == Player.STATE_ENDED) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false // default icon buttons off
                        layoutParams = FrameLayout.LayoutParams(
                            MATCH_PARENT, MATCH_PARENT,
                        )
                    }
                },
                modifier = Modifier.clickable { iconVisible = !iconVisible }
            )

            CustomButtonIcons(
                iconVisible = iconVisible,
                exoPlayer = exoPlayer,
                isPlaying = isPlaying,
                onPlayPauseReplay = {
                    isPlaying = if (isPlaying) {
                        exoPlayer.pause()
                        !isPlaying
                    } else {
                        exoPlayer.play()
                        !isPlaying
                    }
                },
                onPlay = { isPlaying = true },
                playbackState = playbackState,
                videoTimer = videoTimer,
                totalDuration = totalDuration,
                bufferedPercentage = { bufferedPercentage }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            TextView(
                text = title.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextView(text = description.toString())
            Spacer(modifier = Modifier.height(10.dp))
            TextView(
                text = videoUrls.toString(),
                color = Color.Blue
            )
        }
    }
}

@Composable
fun CustomButtonIcons(
    iconVisible: Boolean,
    exoPlayer: ExoPlayer,
    isPlaying: Boolean,
    onPlayPauseReplay: () -> Unit,
    onPlay: () -> Unit,
    playbackState: Int,
    videoTimer: Long,
    totalDuration: Long,
    bufferedPercentage: () -> Int,
) {
    // setting
    var expandedSetting by remember { mutableStateOf(false) }
    // speed
    var plackSpeedExpanded by remember { mutableStateOf(false) }
    // audio
    var audioExpanded by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = if (expandedSetting) true else if (plackSpeedExpanded || audioExpanded) true else iconVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopSettingActionView(
                expandedSetting = expandedSetting,
                onExpandedSetting = { expandedSetting = true },
                onDismissDropdown = { expandedSetting = false },
                plackSpeedExpanded = plackSpeedExpanded,
                onSpeedMenuShow = { plackSpeedExpanded = true },
                onSpeedDismiss = { plackSpeedExpanded = false },
                setPlaySpeed = { speed ->
                    exoPlayer.setPlaybackSpeed(speed as Float)
                },
                audioExpanded = audioExpanded,
                onAudioMenuShow = { audioExpanded = true },
                onAudioDismiss = { audioExpanded = false },
            )
            CenterPlayActionView(
                // play and stop
                isPlaying = isPlaying,
                onPlayAndPause = {
                    onPlayPauseReplay()
                },
                // back to previous
                onForward10 = { exoPlayer.seekBack() },
                // next video play
                onReplay10 = { exoPlayer.seekForward() },
                // replay the same video
                onReplay = {
                    exoPlayer.seekTo(0)
                    exoPlayer.play()
                    onPlay()
                },
                playState = playbackState
            )
            BottomIconsActionView(
                videoTimer = videoTimer,
                totalDuration = totalDuration,
                onSeekToChange = { time: Float ->
                    exoPlayer.seekTo(time.toLong())
                },
                bufferedPercentage = { bufferedPercentage() }
            )
        }
    }
}

// Setting icon button with speed and audio
@Composable
fun TopSettingActionView(
    expandedSetting: Boolean,
    onExpandedSetting: () -> Unit,
    onDismissDropdown: () -> Unit,
    plackSpeedExpanded: Boolean,
    onSpeedMenuShow: () -> Unit,
    onSpeedDismiss: () -> Unit,
    setPlaySpeed: (Any?) -> Unit,
    audioExpanded: Boolean,
    onAudioMenuShow: () -> Unit,
    onAudioDismiss: () -> Unit,
) {
    val optionList = listOf(
        ListOfSettingMenus.Speed,
        ListOfSettingMenus.Audio,
    )

    val playbackSpeedOptions = listOf(0.25f, 0.5f, 0.75f, 1f, 1.25f, 1.5f, 1.75f, 2f)

    val currentPlaybackSpeed by remember { mutableStateOf(playbackSpeedOptions) }

    var speedIndex by remember { mutableIntStateOf(3) } // Set index to 3 to get the value 1f

    var showsSpeed by remember { mutableFloatStateOf(playbackSpeedOptions[speedIndex]) }

    LaunchedEffect(speedIndex) { showsSpeed = playbackSpeedOptions[speedIndex] }

    // audio
    val audioOptions = listOf("Auto", "Unknow")

    var audioIndex by remember { mutableIntStateOf(0) }

    var showsAudio by remember { mutableStateOf(audioOptions[audioIndex]) }

    LaunchedEffect(audioIndex) { showsAudio = audioOptions[audioIndex] }

    // top action icons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalArrangement = Arrangement.End
    ) {
        // setting icon button
        IconButton(modifier = Modifier
            .size(32.dp, 32.dp)
            .padding(end = 8.dp, top = 12.dp),
            onClick = { onExpandedSetting() }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }

        // Dropdown menu for selecting playback speed options
        if (expandedSetting) {
            Box(modifier = Modifier) {
                DropdownMenu(
                    modifier = Modifier.background(color = Color.Black),
                    expanded = true, // expandedSetting
                    onDismissRequest = { onDismissDropdown() }
                ) {
                    optionList.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                when (option.name) {
                                    "Speed" -> {
                                        onSpeedMenuShow()
                                    }

                                    else -> {
                                        onAudioMenuShow()
                                    }
                                }
                                onDismissDropdown()
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    modifier = Modifier.size(width = 24.dp, height = 24.dp),
                                    onClick = {  }
                                ) {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        imageVector = option.icon,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                Column(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TextView(
                                        text = option.name,
                                        color = Color.White,
                                        fontSize = 13.sp,
                                        style = TextStyle(lineHeight = 20.sp),
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    when (option.name) {
                                        "Speed" -> TextView(
                                            text = if (showsSpeed == 1.0f) "Normal" else showsSpeed.toString() + "x",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                        "Audio" -> TextView(
                                            text = showsAudio,
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //playback speed menu items
        if (plackSpeedExpanded) {
            Box(modifier = Modifier, contentAlignment = Alignment.CenterEnd) {
                DropdownMenu(
                    modifier = Modifier.background(color = Color.Black),
                    expanded = true, // plackSpeedExpanded,
                    onDismissRequest = { onSpeedDismiss() } // plackSpeedExpanded = false
                ) {
                    currentPlaybackSpeed.forEachIndexed { index, speed ->
                        DropdownMenuItem(
                            onClick = {
                                speedIndex = index // Set the selected index
                                setPlaySpeed.invoke(speed)
                                onSpeedDismiss()
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                if (speedIndex == index) {
                                    Icon(
                                        modifier = Modifier,
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "write",
                                        tint = Color.White
                                    )
                                }
                                TextView(
                                    text = if (speed == 1.0f) "Normal" else speed.toString() + "x",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // show video quality menu items
        if (audioExpanded) {
            Box(modifier = Modifier, contentAlignment = Alignment.CenterEnd) {
                DropdownMenu(
                    modifier = Modifier.background(color = Color.Black),
                    expanded = true, // audioExpanded,
                    onDismissRequest = { onAudioDismiss() } // audioExpanded = false
                ) {
                    audioOptions.forEachIndexed { index, quality ->
                        DropdownMenuItem(
                            onClick = {
                                audioIndex = index // Set the selected index
                                onAudioDismiss()
//                                audioExpanded = false
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                if (audioIndex == index) {
                                    Icon(
                                        modifier = Modifier,
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "write",
                                        tint = Color.White
                                    )
                                }
                                TextView(
                                    text = quality,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// video play, pause, replay, forward and skip button
@Composable
fun CenterPlayActionView(
    isPlaying: Boolean,
    onPlayAndPause: () -> Unit,
    onForward10: () -> Unit,
    onReplay10: () -> Unit,
    onReplay: () -> Unit,
    playState: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // back or Skip Previous
            IconButton(onClick = { onForward10() }) {
                Icon(
                    imageVector = Icons.Default.Forward10,
                    contentDescription = "Forward 10 seconds",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
            // play, pause and replay
            IconButton(
                onClick = {
                    if (playState == Player.STATE_ENDED) {
                        onReplay()
                    } else {
                        onPlayAndPause()
                    }
                }
            ) {
                Icon(
                    imageVector = when {
                        isPlaying -> {
                            Icons.Default.Pause
                        }

                        ((playState == Player.STATE_ENDED && !isPlaying)) -> {
                            Icons.Default.Replay
                        }

                        else -> {
                            Icons.Default.PlayArrow
                        }
                    },
                    contentDescription = "play icon state",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
            // Skip Next video
            IconButton(onClick = { onReplay10() }) {
                Icon(
                    imageVector = Icons.Default.Replay10,
                    contentDescription = "Replay 10 seconds",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomIconsActionView(
    videoTimer: Long,
    totalDuration: Long,
    onSeekToChange: (Float) -> Unit,
    bufferedPercentage: () -> Int
) {
    val context = LocalContext.current
    val activity = context as? Activity

    var currentTimer by remember { mutableStateOf("") }
    LaunchedEffect(videoTimer, totalDuration) {
        // Calculate minutes, and seconds
        val seconds = (videoTimer / 1000) % 60
        val minutes = (videoTimer / (1000 * 60)) % 60
        currentTimer = String.format("%02d:%02d", minutes, seconds)
    }

    var totalDurationTime by remember { mutableStateOf("") }
    LaunchedEffect(totalDuration) {
        // Calculate minutes, and seconds
        val seconds = (totalDuration / 1000) % 60
        val minutes = (totalDuration / (1000 * 60)) % 60
        totalDurationTime = String.format("%02d:%02d", minutes, seconds)
//        val hours = (totalDuration / (1000 * 60 * 60))
//        // Format the time as HH:MM:SS
//        totalDurationTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    // Fullscreen state
    var landScapeScreen by remember { mutableStateOf(true) }
    val onConvertLandScape = {
        if (landScapeScreen) {
            activity?.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity?.requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
    val changeBuffer =
        remember(bufferedPercentage()) { bufferedPercentage() } // Corrected the state type to Float
    val interactionSource = remember { MutableInteractionSource() }
    val colors = SliderDefaults.colors(
        thumbColor = Color.Red,
        activeTrackColor = Color.Red,
        inactiveTrackColor = Color.Transparent,
    )
    // duration slider and zoom
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
        // show Full screen and Exit screen
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // show the time
            Row(modifier = Modifier.wrapContentWidth()) {
                Text(
                    text = currentTimer, // videoTimer.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = "/",
                    color = Color.White,
                    modifier = Modifier.padding(start = 3.dp)
                )
                Text(
                    text = totalDurationTime, // totalDuration.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }
            IconButton(
                onClick = {
                    landScapeScreen = !landScapeScreen
                    onConvertLandScape()
                }
            ) {
                Icon(
                    imageVector = if (landScapeScreen) Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        // slider
        Column(modifier = Modifier.height(5.dp)) {
            Box {
                Slider(
                    value = changeBuffer.toFloat(),
                    enabled = false,
                    onValueChange = { },
                    interactionSource = interactionSource,
                    valueRange = 0f..100f,
                    colors = SliderDefaults.colors(
                        disabledThumbColor = Color.Transparent,
                        disabledActiveTrackColor = Color.White,
                        activeTickColor = Color.White,
                    )
                )

                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = videoTimer.toFloat(),
                    onValueChange = { onSeekToChange },
                    valueRange = 0f..totalDuration.toFloat(),
                    colors = colors,
                    interactionSource = interactionSource,
                    thumb = {
                        SliderDefaults.Thumb(
                            interactionSource = interactionSource,
                            colors = colors,
                            modifier = Modifier.size(15.dp)
                        )
                    },
                )
            }
        }
    }
}

open class ListOfSettingMenus(var icon: ImageVector, val name: String) {
    object Speed : ListOfSettingMenus(Icons.Default.Settings, "Speed")
    object Audio : ListOfSettingMenus(Icons.Default.Audiotrack, "Audio")
}