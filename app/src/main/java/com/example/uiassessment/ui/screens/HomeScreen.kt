package com.example.uiassessment.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.uiassessment.R
import com.example.uiassessment.navigation.AddFoodRef
import com.example.uiassessment.rememberWindowSize
import com.example.uiassessment.ui.FoodCard
import com.example.uiassessment.ui.SearchBar
import com.example.uiassessment.ui.SelectableOptionsSection

import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.searchBarColor
import com.example.uiassessment.ui.theme.smallTextLight


@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    var selectedPosition  by rememberSaveable { mutableStateOf(1) }



    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)

                .fillMaxSize()

        ) {


            val windowSizeInfo = rememberWindowSize()

            LaunchedEffect(true) {

                Log.d("WindowSizee", "HomeScreen: ${windowSizeInfo.widthInfo}")

            }
            // Top Bar
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {

                Image(

                    painter = painterResource(id = R.drawable.lucy), // Replace with your profile image

                    contentDescription = "Profile Picture",

                    modifier = Modifier

                        .size(42.dp)


                )

                IconButton(onClick = { /* Handle notification click */ }) {

                    Icon(
                        painter = painterResource(R.drawable.notification_icon),
                        contentDescription = "Notifications",
                        modifier = Modifier.size(40.dp)
                    )

                }

            }



            Spacer(modifier = Modifier.height(16.dp))

            Text("Hey there, Lucy!", style = LocalFonts.current.titleBold)

            Text(
                "Are you excited to create a tasty dish today?",
                style = LocalFonts.current.bodyRegularLight
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))
            SearchBar()

            Spacer(modifier = Modifier.padding(top = 10.dp))
            SelectableOptionsSection(selectedPosition) { selectedPosition = it }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Box(modifier = Modifier
                .height(height = 8.dp)
                .fillMaxWidth()
                .background(searchBarColor))

            Text(
                text = "All Foods",
                style = LocalFonts.current.title,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            LazyColumn {
                items(5) {
                    FoodCard()
                }


            }
        }

        Icon(painter = painterResource(R.drawable.add_square),
            modifier = Modifier.padding(40.dp).size(56.dp).clickable { navController.navigate(AddFoodRef) }.align(Alignment.BottomEnd),
           contentDescription = "Add Food")


    }
}



