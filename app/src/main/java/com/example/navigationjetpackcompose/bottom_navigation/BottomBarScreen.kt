package com.example.navigationjetpackcompose.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : BottomBarScreen(
        "home",
        "Home",
        icon = Icons.Default.Home
    )
    object Search : BottomBarScreen(
        "search?code={code}",
        "Search",
        icon = Icons.Default.Search
    ){
        fun getDestinationWithValue(argument:String):String{
            return "search?code=$argument"
        }
    }
   /* object Add : BottomBarScreen(
        "add",
        "Add",
        icon = Icons.Default.Add
    )*/
    object Profile : BottomBarScreen(
        "profile",
        "Profile",
        icon = Icons.Default.Person
    )
    object Notification : BottomBarScreen(
        "notification",
        "Notification",
        icon = Icons.Default.Notifications
    )
}
