package com.example.uiassessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uiassessment.ui.screens.AddScreen
import com.example.uiassessment.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRef


@Serializable
object AddFoodRef


@Composable
fun BaseNavHost(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeRef){
        composable<HomeRef> { HomeScreen(navController)  }
        composable<AddFoodRef> { AddScreen()  }
    }

}