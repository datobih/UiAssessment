package com.example.uiassessment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.uiassessment.R
import com.example.uiassessment.ui.CustomDescriptionTextField
import com.example.uiassessment.ui.CustomMenuTextField
import com.example.uiassessment.ui.CustomTextField
import com.example.uiassessment.ui.TagsField
import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.borderGrey
import com.example.uiassessment.ui.theme.darkBlue
import com.example.uiassessment.ui.theme.disabledButtonColor
import com.example.uiassessment.ui.theme.headerCircle
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.smallTextLight

@Composable
fun AddScreen(){

    var foodNameState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }
    var categoryState by remember { mutableStateOf("") }
    var caloriesState by remember { mutableStateOf("") }
    var tagsState by remember { mutableStateOf("") }


    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.fillMaxSize()) {
            AddNewFoodHeader()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Column(modifier = Modifier
                    .border(1.dp, headerCircle, RoundedCornerShape(4.dp))
                    .clickable {

                    }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = "Back",
                        tint = darkBlue, // Black arrow icon color
                        modifier = Modifier.size(24.dp) // Size of the arrow icon itself
                    )
                    Text(
                        "Take photo",
                        style = LocalFonts.current.bodyRegular,
                        modifier = Modifier.padding(top = 4.dp)
                    )


                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier
                    .border(1.dp, headerCircle, RoundedCornerShape(4.dp))
                    .clickable {

                    }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(R.drawable.upload),
                        contentDescription = "Back",
                        tint = darkBlue, // Black arrow icon color
                        modifier = Modifier.size(24.dp) // Size of the arrow icon itself
                    )
                    Text(
                        "Take photo",
                        style = LocalFonts.current.bodyRegular,
                        modifier = Modifier.padding(top = 4.dp)
                    )


                }

            }


            Spacer(modifier = Modifier.height(16.dp))




            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                Text(
                    text = "Name",
                    style = LocalFonts.current.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp)) // Spacing between label and input field
                CustomTextField(foodNameState, "Enter food name",
                    { text ->
                        foodNameState = text
                    }, modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Description",
                    style = LocalFonts.current.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                CustomDescriptionTextField(
                    descriptionState, "Enter food description",
                    { text -> descriptionState = text },
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Category",
                    style = LocalFonts.current.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp)) // Spacing between label and input field
                CustomMenuTextField(categoryState, "Enter Category") {
                    categoryState = it
                }

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Calories",
                    style = LocalFonts.current.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp)) // Spacing between label and input field
                CustomTextField(caloriesState, "Enter number of calories",
                    { text -> caloriesState = text }, modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Tags",
                    style = LocalFonts.current.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                TagsField()
            }




            Button(
                onClick = {},
                colors = ButtonColors(
                    containerColor = highlightBlue,
                    contentColor = Color.White,
                    disabledContainerColor = disabledButtonColor,
                    disabledContentColor = smallTextLight
                ),
                enabled = false,
                modifier = Modifier.padding(top = 80.dp, bottom = 16.dp)
                    .fillMaxWidth(.93f)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(4.dp)
            ) {

                Text("Add food", modifier = Modifier.padding(vertical = 6.dp), style = LocalFonts.current.bodyRegularLightAlt)

            }

        }
    }


}







@Composable
fun AddNewFoodHeader() {
    Column(modifier = Modifier.fillMaxWidth()) { // Main Column to hold header and separator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp), // Horizontal padding and vertical for top/bottom spacing
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button (Icon Button with Circle Background)
            IconButton(
                onClick = { /*TODO: Handle back navigation*/ },
                modifier = Modifier
                    .border(1.dp, headerCircle, CircleShape)
                    .size(40.dp) // Size of the IconButton and circle
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_square_back),
                    contentDescription = "Back",
                    tint = Color.Black, // Black arrow icon color
                    modifier = Modifier.size(20.dp) // Size of the arrow icon itself
                )
            }
            Spacer(modifier = Modifier.width(12.dp)) // Spacing between back button and text


            Text(
                text = "Add new food",
                style = LocalFonts.current.title
            )
        }

        // Separator Line
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(borderGrey) // Light gray separator line
             // Indent line to match header padding if needed
        )




    }
}