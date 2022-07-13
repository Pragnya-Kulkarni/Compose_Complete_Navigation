package com.example.navigationjetpackcompose.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigationjetpackcompose.Person

@Composable
fun SecondPost(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val result= navController.previousBackStackEntry?.savedStateHandle?.get<Person>("person")

            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "This is about second post", fontSize = 40.sp, fontStyle = FontStyle.Italic)

               result?.let {  Text(text = "${result.id} ${result.name}", fontSize = 40.sp, fontStyle = FontStyle.Italic)}
            }
    }
}
