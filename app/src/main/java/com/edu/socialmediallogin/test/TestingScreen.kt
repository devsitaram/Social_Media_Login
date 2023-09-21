package com.edu.socialmediallogin.test

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edu.socialmediallogin.presentation.components.ButtonView
import com.edu.socialmediallogin.presentation.components.InputTextFieldView
import com.edu.socialmediallogin.presentation.Navigation.ScreenList

@Composable
fun TestingViewScreen(navController: NavHostController) {

    val context = LocalContext.current
//    val viewModel: SubjectViewModel = hiltViewModel()

    var photoUrl by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // photoUrl
        InputTextFieldView(
            value = photoUrl,
            onValueChange = { photoUrl = it },
            label = "photoUrl",
            placeholder = "photoUrl",
            textStyle = TextStyle(),
            errorMessage = "",
            invalidMessage = "",
            modifier = Modifier.fillMaxWidth()
        )
        // name
        InputTextFieldView(
            value = name,
            onValueChange = { name = it },
            label = "name",
            placeholder = "name",
            textStyle = TextStyle(),
            errorMessage = "",
            invalidMessage = "",
            modifier = Modifier.fillMaxWidth()
        )
        // description
        InputTextFieldView(
            value = description,
            onValueChange = { description = it },
            label = "description",
            placeholder = "description",
            textStyle = TextStyle(),
            errorMessage = "",
            invalidMessage = "",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))
        ButtonView(
            onClick = {
                if (name.isNotEmpty() && description.isNotEmpty()) {
//                    viewModel.insertSubject(
//                        photoUrl = photoUrl, //.ifEmpty { DEFAULT_IMAGE_URL },
//                        name = name,
//                        description = description,
//                        isIvy = false
//                    )
                    navController.navigate(ScreenList.SubjectScreen.route)
                } else {
                    Toast.makeText(context, "The fields is empty", Toast.LENGTH_SHORT).show()
                }
            },
            btnColor = Color.Blue,
            text = "Register",
            textStyle = TextStyle(fontWeight = FontWeight.Bold, color = Color.White)
        )
    }
}