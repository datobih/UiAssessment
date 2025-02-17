package com.example.uiassessment.navigation



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

import kotlinx.serialization.Serializable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import com.example.uiassessment.R

@Serializable
object HomeScreenRef

// Home level routes
@Serializable
object HomeLevelHomeRef
@Serializable
object HomeLevelGeneratorRef
@Serializable
object HomeLevelAddRef


@Serializable
object HomeLevelFavouriteRef


@Serializable
object HomeLevelPlannerRef


@Serializable
object Profile

@Serializable
object EditProfile


@Serializable
object Posts

@Serializable
object AddPost




data class HomeLevelRoute<T:Any>(val name:String,val route:T,val icon:Int,val selectedIcon:Int)

val homeLevelRoutes = listOf(
    HomeLevelRoute("Home",HomeLevelHomeRef, R.drawable.home,R.drawable.home_highlighted),
    HomeLevelRoute("Generator",HomeLevelGeneratorRef,R.drawable.magicpen,R.drawable.magicpen),
    HomeLevelRoute("Add",HomeLevelAddRef,R.drawable.add,R.drawable.add),
    HomeLevelRoute("Favourite",HomeLevelFavouriteRef, R.drawable.heart,R.drawable.heart),
    HomeLevelRoute("Planner",HomeLevelPlannerRef,R.drawable.calendar,R.drawable.calendar)
)



@Composable
fun HomeNavHost(parentNavController: NavHostController){

    val homeNavController = rememberNavController()

    Scaffold(

        bottomBar = { BottomNavigation(backgroundColor = Color.White) {

            val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            homeLevelRoutes.forEach { homeLevelRoute->
                BottomNavigationItem(icon = {}, label = {}, onClick = {},selected = currentDestination?.hierarchy?.any {  it.hasRoute(homeLevelRoute.route::class) } == true)
            }
        } }
    ) { innerPadding ->

        NavHost(homeNavController, startDestination = HomeLevelHomeRef, modifier = Modifier.padding(innerPadding)){
            composable<HomeLevelHomeRef> {  }
            composable<HomeLevelGeneratorRef>{  }
            composable<HomeLevelAddRef> {  }
            composable<HomeLevelFavouriteRef> {  }
            composable<HomeLevelPlannerRef> {  }
        }
    }



}