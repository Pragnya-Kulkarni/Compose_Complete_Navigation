package com.example.navigationjetpackcompose.bottom_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.navigationjetpackcompose.composables.*

class BottomNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {

        //6. define home as first destination as it is initial screen
        //7. pass navController to the constructor of each composable which is needed for navigation
        val uri = "https://www.example.com"
        composable(
            "home?id={id}",
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" }),
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = "0"
            })
        ) {
                backStackEntry ->
            HomeScreen(navController, backStackEntry.arguments?.getString("id"))
        }

        composable(BottomBarScreen.Search.route, arguments = listOf(navArgument("code"){
            type= NavType.StringType
            defaultValue= ""
        })) {
            val post= it.arguments?.getString("code")
            SearchScreen(navController, post)
        }
        /*composable(BottomBarScreen.Add.route) {
            AddScreen(navController)
        }*/
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }



        // Use of Deep Link in compose
       /* val uri = "https://www.example.com"
        composable(
            "profile?id={id}",
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })
        ) { backStackEntry ->
            Profile(navController, backStackEntry.arguments?.getString("id"))
        }*/


        composable(BottomBarScreen.Notification.route) {
            NotificationScreen(navController, "0")
        }


        composable("first_post?postItem={postItem}", arguments = listOf(navArgument("postItem"){
            type= NavType.StringType
            defaultValue= "Hello"
        })) {
            val post= it.arguments?.getString("postItem")
            FirstPost(post)
        }
       /* composable("first_post?postItem={postItem}") {
            FirstPost()
        }*/
        composable("second_post") {
            SecondPost(navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        /* BottomBarScreen.Add,*/
        BottomBarScreen.Notification,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation() {
        screens.forEach { screen ->
            BottomNavigationItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = "Nav Icon")
                },
                selected = currentDestination?.hierarchy?.any { navDestination -> navDestination.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id)
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                         launchSingleTop = true
                    }
                })
        }
    }
}
