package com.example.navigationjetpackcompose

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigationjetpackcompose.composables.*
import kotlinx.android.parcel.Parcelize

class MainActivity : ComponentActivity() {
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
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // 2. to get navcontroller we have method called rememberNavController
        val navController = rememberNavController()

        //1. set nav host, use the nav host which has 3 parameters navcontroller, startdestination, navgraphbuilder
        NavHost(
            navController = navController,// 3. assign navController
            startDestination = "home"// 4. home to show it as initial screen
        ) {
            //5. here we need put all the screens that we want for the navigation graph
            // we have a function called composable()   and it is extension of navgraph builder
            // (command+B on composable to see extension method)
            // deep links and arguments have default values as empty so optional param


            //6. define home as first destination as it is initial screen
            //7. pass navController to the constructor of each composable which is needed for navigation
            composable("home") {
                HomeScreen(navController, "0")
            }
            composable("first_post?postItem={postItem}", arguments = listOf(navArgument("postItem"){
                type= NavType.StringType
                defaultValue= "Hello" // either write default value or nullable=true
            })) {
                val post= it.arguments?.getString("postItem")
                FirstPost(post)
            }
            composable("second_post") {
               /* val result= navController.previousBackStackEntry?.savedStateHandle?.get<Person>("person")
                result?.id
                result?.name*/
                SecondPost(navController)
            }
        }

    }
}

@Parcelize
data class Person(val id:Int, val name:String):Parcelable

