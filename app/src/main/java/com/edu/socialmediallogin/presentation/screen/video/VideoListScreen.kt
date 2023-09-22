package com.edu.socialmediallogin.presentation.screen.video

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.edu.socialmediallogin.presentation.components.AsyncImageView
import com.edu.socialmediallogin.presentation.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.components.TextView

@Composable
fun VideoListViewScreen() {
    val navController = rememberNavController()
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

//            videos.isData?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                items(5) {
                    VideoCardView(imageUrl = "https://nepal-assets-apollo.mysecondteacher.com/images/E1.3_c3228618-f880-4a42-9f37-2aed9c580202_69774_.jpg") {
                    }
                }
            }
//        }
        }
    }
}

@Composable
fun VideoCardView(
    imageUrl: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp,
                bottom = 20.dp)
            .clickable { onClick() },
    ) {
        Box(modifier = Modifier.wrapContentSize().padding(5.dp), contentAlignment = Alignment.BottomCenter) {
            AsyncImageView(
                model = imageUrl,
                modifier = Modifier
            )
//            VectorIconView(imageVector = Icons.Default.PlayCircleOutline, tint = Color.White)
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
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
            text = "Introduction to Grade 11 Compulsory English is Compulsory English",
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