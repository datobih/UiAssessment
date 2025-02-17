package com.example.uiassessment.navigation



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

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
import androidx.compose.ui.unit.dp
import com.example.uiassessment.R
import com.example.uiassessment.ui.screens.AddScreen
import com.example.uiassessment.ui.screens.FavouriteScreen
import com.example.uiassessment.ui.screens.GeneratorScreen
import com.example.uiassessment.ui.screens.HomeScreen
import com.example.uiassessment.ui.screens.PlannerScreen
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.smallTextLight
import com.example.uiassessment.ui.theme.thememodels.LocalFonts

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
fun HomeNavHost(){

    val homeNavController = rememberNavController()

    Scaffold(

        bottomBar = { BottomNavigation(backgroundColor = Color.White) {

            val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            homeLevelRoutes.forEach { homeLevelRoute->
                val isSelected =currentDestination?.hierarchy?.any {  it.hasRoute(homeLevelRoute.route::class)}

                BottomNavigationItem(icon = {


                        if(homeLevelRoute.route == HomeLevelHomeRef && isSelected == true){
                            Icon(
                                painter = painterResource(R.drawable.home_highlighted),
                                contentDescription = "Bottom Navigation Icon",
                                modifier = Modifier
                                    .padding(top = 22.dp, bottom = 2.dp)
                                    .size(20.dp),
                                tint =  highlightBlue
                            )
                        }
                        else{
                            Icon(
                                painter = painterResource(homeLevelRoute.icon),
                                contentDescription = "Bottom Navigation Icon",
                                modifier = Modifier
                                    .padding(top = 22.dp, bottom = 2.dp)
                                    .size(20.dp),
                                tint = if(isSelected == true) highlightBlue else smallTextLight
                            )
                        }






                }, label = {Text(homeLevelRoute.name, style =if(isSelected == true) LocalFonts.current.navHighlightedText else LocalFonts.current.navText, modifier = Modifier.padding(bottom = 22.dp))}, onClick = {
                    homeNavController.navigate(homeLevelRoute.route){

                        popUpTo(homeNavController.graph.startDestinationId){
                            saveState = true
                        }

                        restoreState = true
                        launchSingleTop =true

                    } },selected = currentDestination?.hierarchy?.any {  it.hasRoute(homeLevelRoute.route::class) } == true)
            }
        } }
    ) { innerPadding ->

        NavHost(homeNavController, startDestination = HomeLevelHomeRef, modifier = Modifier.padding(innerPadding)){
            composable<HomeLevelHomeRef> { HomeScreen() }
            composable<HomeLevelGeneratorRef>{ GeneratorScreen() }
            composable<HomeLevelAddRef> { AddScreen() }
            composable<HomeLevelFavouriteRef> { FavouriteScreen() }
            composable<HomeLevelPlannerRef> { PlannerScreen() }
        }
    }



}