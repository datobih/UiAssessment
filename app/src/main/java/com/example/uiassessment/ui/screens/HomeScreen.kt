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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lostandfound.utils.UIState
import com.example.uiassessment.R
import com.example.uiassessment.models.FoodDTO
import com.example.uiassessment.models.FoodRequestDTO
import com.example.uiassessment.models.FoodResponseDTO
import com.example.uiassessment.navigation.AddFoodRef
import com.example.uiassessment.rememberWindowSize
import com.example.uiassessment.ui.FoodCard
import com.example.uiassessment.ui.SearchBar
import com.example.uiassessment.ui.SelectableOptionsSection

import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.disabledButtonColor
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.searchBarColor
import com.example.uiassessment.ui.theme.smallTextLight
import com.example.uiassessment.ui.theme.titleBlack
import com.example.uiassessment.viewmodel.MainViewModel


@Composable
fun HomeScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    val context = LocalContext.current
    var selectedPosition  by rememberSaveable { mutableStateOf(1) }

    val getFoodsState by mainViewModel.getFoodsLiveData.observeAsState()

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
            SearchBar({mainViewModel.filterFoods(it)})

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


            when(getFoodsState){
                is UIState.ErrorState -> {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Something went wrong", style = LocalFonts.current.title)

                        Button(
                            onClick = {

                                mainViewModel.getFoods()

                            },
                            colors = ButtonColors(
                                containerColor = highlightBlue,
                                contentColor = Color.White,
                                disabledContainerColor = disabledButtonColor,
                                disabledContentColor = smallTextLight
                            ),

                            modifier = Modifier
                                .padding(top = 30.dp)
                                .fillMaxWidth(.4f)
                                .align(Alignment.CenterHorizontally),

                            shape = RoundedCornerShape(4.dp)
                        ) {

                            androidx.compose.material3.Text(
                                "Try Again",
                                modifier = Modifier.padding(vertical = 6.dp),
                                style =  LocalFonts.current.bodyRegularBoldWhite
                            )

                        }


                    }
                }
                is UIState.InitialState -> {
                  LaunchedEffect(getFoodsState) {
                      mainViewModel.getFoods()
                  }
                }
                is UIState.LoadingState -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator(
                            color = Color.Black,
                            modifier = Modifier
                                .width(40.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
                is UIState.SuccessState -> {

                    (getFoodsState as UIState.SuccessState<FoodResponseDTO?>).data?.let {
                        foodResponse->
                        LaunchedEffect(getFoodsState) {
                            mainViewModel.filteredFoods.clear()
                            mainViewModel.filteredFoods.addAll(foodResponse.data)
                        }

                        if(!(mainViewModel.filteredFoods.isNullOrEmpty())){
                            LazyColumn {
                                items(mainViewModel.filteredFoods.toList()) {
                                    FoodCard(it)

                                }


                            }
                        }
                        else{

                        }
                    }



                }
                else -> {}
            }

        }

        Icon(painter = painterResource(R.drawable.add_square),
            modifier = Modifier
                .padding(40.dp)
                .size(56.dp)
                .clickable {
                    navController.navigate(AddFoodRef)

                }
                .align(Alignment.BottomEnd),
           contentDescription = "Add Food")


    }
}



