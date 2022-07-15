package com.example.navigationjetpackcompose.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigationjetpackcompose.Person


@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, id: String?) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            var message by remember { mutableStateOf("") }

            TextField(value = message, onValueChange = { message = it })
            Spacer(modifier = Modifier.height(30.dp))
            Card(modifier = Modifier
                .height(100.dp)
                .width(250.dp),
                backgroundColor = Color.Magenta.copy(alpha = 0.2F),
                onClick = {
                    navController.navigate("first_post?postItem=$message")
                }
            ) {
                Text(
                    text = "First Post",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Card(modifier = Modifier
                .height(100.dp)
                .width(250.dp),
                backgroundColor = Color.Blue.copy(alpha = 0.2F),
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("person", Person(1,"Name1"))
                    navController.navigate("second_post")
                }
            ) {
                Text(
                    text = "Second Post",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            id?.let {
                Text(
                    text = "Id Sent is $id",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
                )
            }

        }
    }


}
