package com.example.navigationjetpackcompose.bottom_navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NotificationScreen(navController: NavHostController, id: String?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Notification Screen", fontSize = 40.sp, fontStyle = FontStyle.Italic)
            Button(
                onClick = {
                    navController.navigate(BottomBarScreen.Profile.route)
                }) {
                Text(text = "Navigate To Profile")
            }
            id?.let {  Text(text =id, fontSize = 40.sp, fontStyle = FontStyle.Italic) }
        }
    }
}