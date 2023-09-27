package com.edu.socialmediallogin.presentation.ui.screen

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.room.ColumnInfo
import com.edu.socialmediallogin.data.common.Constants.HTTPS_IMAGE_BASE_URL
import com.edu.socialmediallogin.data.source.local.SubjectEntity
import com.edu.socialmediallogin.presentation.ui.components.AsyncImageView
import com.edu.socialmediallogin.presentation.ui.components.ButtonAppBar
import com.edu.socialmediallogin.presentation.ui.components.TextView
import com.edu.socialmediallogin.presentation.ui.components.VectorIconView
import com.edu.socialmediallogin.presentation.viewmodel.SubjectViewModel
import com.edu.socialmediallogin.ui.theme.skyBlue

@Composable
fun SubjectViewScreen(
    navController: NavHostController,
    viewModel: SubjectViewModel = hiltViewModel()
) {
//    val context = (LocalContext.current as Activity)

    val subjects = viewModel.subjectList.value

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getSubject()
    })

    if (subjects.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (subjects.isError.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = subjects.isError)
        }
    }

    subjects.isData?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonAppBar(title = "", navController = navController)
                TextView(
                    text = "Subjects",
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
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    items(it) { subject ->
                        val subjectId = subject?.subjectId
                        val yearlyPrice = subject?.yearlyPrice
                        val studentSubject = subject?.studentSubject
                        val validityStartDate = subject?.validityStartDate
                        val level = subject?.level
                        val packageId = subject?.packageId
                        val packageTag = subject?.packageTag
                        val monthlyPrice = subject?.monthlyPrice
                        val validityEndDate = subject?.validityEndDate
                        val halfYearlyPrice = subject?.halfYearlyPrice
                        val assetType = subject?.assetType
                        val photoUrl = subject?.photoUrl.toString()
                        val isComingSoon = subject?.isComingSoon
                        val name = subject?.name
                        val planEndDate = subject?.planEndDate
                        val packageGrade = subject?.packageGrade
                        val isStudentPremium = subject?.isStudentPremium
                        val order = subject?.order

                        SubjectCardView(
                            imageUrl = HTTPS_IMAGE_BASE_URL + photoUrl,
                            name = name,
                            studSubjName = subject?.studentSubject?.subjectName,
                            planEndDate = planEndDate,
                            onClickable = {
                                 navController.navigate("VideoListScreen/${subjectId}/${name}")
                            },
                            onDelete = {
//                                viewModel.deleteSubject(id = subjectId.toInt())
//                                // Trigger recomposition by updating the state
//                                viewModel.updateSubjectListAfterDelete(subjectId.toInt())
                            }
                        )

                        // insert the data in local server if any change the remote server
                        LaunchedEffect(key1 = subjectId, block = {
                            val listOfSubjects = listOf(
                                SubjectEntity(
                                    subjectId = subjectId,
                                    yearlyPrice = yearlyPrice,
                                    studentSubject = studentSubject,
                                    validityStartDate = validityStartDate,
                                    level = level,
                                    packageId = packageId,
                                    packageTag = packageTag,
                                    monthlyPrice = monthlyPrice,
                                    validityEndDate = validityEndDate,
                                    halfYearlyPrice = halfYearlyPrice,
                                    assetType = assetType,
                                    photoUrl = photoUrl,
                                    isComingSoon = isComingSoon,
                                    name = name,
                                    planEndDate = planEndDate,
                                    packageGrade = packageGrade,
                                    isStudentPremium = isStudentPremium,
                                    order = order
                                )
                            )
                            viewModel.insertSubject(listOfSubjects)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectCardView(
    imageUrl: String,
    name: String?,
    studSubjName: String?,
    planEndDate: String?,
    onClickable: () -> Unit,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .border(1.dp, Color.LightGray)
            .clickable { onClickable() },
        shape = ShapeDefaults.Medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImageView(
                model = imageUrl,
                modifier = Modifier
                    .size(120.dp)
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray),
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    TextView(
                        text = name.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier
                    )
                    TextView(
                        text = studSubjName.toString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDelete
                        }
                        .border(1.dp, Color.LightGray)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VectorIconView(
                        imageVector = Icons.Default.Newspaper,
                        contentDescription = null,
                        tint = skyBlue,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    TextView(
                        text = "View Package Detail", style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = skyBlue
                        ),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
    }
}