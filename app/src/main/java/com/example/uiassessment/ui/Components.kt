package com.example.uiassessment.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.uiassessment.R
import com.example.uiassessment.bottomBorder
import com.example.uiassessment.topBorder
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.optionBgLight
import com.example.uiassessment.ui.theme.searchBarColor
import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.borderGrey
import com.example.uiassessment.ui.theme.tagOrange
import androidx.compose.ui.unit.sp as sp1

@Composable
fun SearchBar() {
    val searchText = remember { mutableStateOf("") }

    TextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier

            .fillMaxWidth()
        , // Optional padding around the search bar itself
        placeholder = { Text("Search foods...", style = LocalFonts.current.bodyRegularLight) }, // Hint text
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray // Icon color
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp), // Rounded corners
        colors = TextFieldDefaults.colors(
            focusedContainerColor = searchBarColor,
            unfocusedContainerColor = searchBarColor,
            disabledContainerColor = searchBarColor,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent, // Remove underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when not focused
        )
    )
}




@Composable
fun OptionElement(text:String,isSelected:Boolean,onSelect:()-> Unit){
    Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).clickable {
        onSelect()
    }.background(if(isSelected) highlightBlue
    else optionBgLight).padding(10.dp)){

        Text(text, style = if(isSelected) LocalFonts.current.optionTextHighlighted
        else LocalFonts.current.optionText)

    }


}


@Composable
fun SelectableOptionsSection(selectedPosition: Int,onOptionSelected: (Int) -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        OptionElement(text = "Option 1", isSelected = selectedPosition == 1, onSelect = {onOptionSelected(1)})

        OptionElement(text = "Option 2", isSelected = selectedPosition == 2, onSelect = {onOptionSelected(2)})

        OptionElement(text = "Option 3", isSelected = selectedPosition == 3, onSelect = {onOptionSelected(3)})

        OptionElement(text = "Option 4", isSelected = selectedPosition == 4, onSelect = {onOptionSelected(4)})
    }
}




@Composable
fun FoodCard() {

        Column(
            modifier = Modifier
                 // Padding inside the card
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // "All Foods" Header

            // Food Image
            Image(
                painter = painterResource(id = R.drawable.lucy), // Replace with your image resource
                contentDescription = "Garlic Butter Shrimp Pasta",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(137.dp) // Adjust height as needed
                    .clip(RoundedCornerShape(8.dp)), // Rounded corners for image
                contentScale = ContentScale.Crop
            )


            Column(modifier = Modifier.bottomBorder(1.dp, borderGrey,4.dp).padding(horizontal = 16.dp, vertical = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Food Title
                    Text(
                        text = "Garlic Butter Shrimp Pasta",
                        style = LocalFonts.current.titleBold,
                    )
                    // Heart Icon
                    Icon(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "Favorite",
                  // Light gray for heart icon
                        modifier = Modifier.size(18.dp)
                    )
                }


                Spacer(modifier = Modifier.height(3.dp))

                // Calories Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fire), // Replace with your fire icon resource
                        contentDescription = "Calories",
                        tint = Color(0xFFF44336), // Reddish color for fire icon
                        modifier = Modifier.size(width = 11.dp, height = 13.5.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "320 Calories",
                        style = LocalFonts.current.bodySmallSecondary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Description Text
                Text(
                    text = "Creamy hummus spread on whole grain toast topped with sliced cucumbers and radishes.",
                    style = LocalFonts.current.bodySmall,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Tags Row
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Tag(text = "healthy") // Light Teal for "healthy" tag
                    Tag(text = "vegetarian") // Light Pink for "vegetarian" tag
                }
            }

        }

}

@Composable
fun Tag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)) // Rounded pill shape for tags
            .background(tagOrange)
            .padding(horizontal = 12.dp, vertical = 6.dp), // Padding inside tags
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp1,
                color = Color(0xFF616161) // Dark gray text for tags
            )
        )
    }
}