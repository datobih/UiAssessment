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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.uiassessment.R
import com.example.uiassessment.bottomBorder
import com.example.uiassessment.topBorder
import com.example.uiassessment.ui.theme.highlightBlue
import com.example.uiassessment.ui.theme.optionBgLight
import com.example.uiassessment.ui.theme.searchBarColor
import com.example.uiassessment.ui.theme.LocalFonts
import com.example.uiassessment.ui.theme.borderGrey
import com.example.uiassessment.ui.theme.smallTextLight
import com.example.uiassessment.ui.theme.tagGray
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
    Box(modifier = Modifier
        .clip(RoundedCornerShape(4.dp))
        .clickable {
            onSelect()
        }
        .background(
            if (isSelected) highlightBlue
            else optionBgLight
        )
        .padding(10.dp)){

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


            Column(modifier = Modifier
                .bottomBorder(1.dp, borderGrey, 4.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)) {
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

@Composable
fun CustomTextField(text: String,placeHolder:String,onTextChange: (String) -> Unit,modifier: Modifier){
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it)},
        modifier = modifier,
        placeholder = {
            androidx.compose.material3.Text(
                placeHolder,
                style = LocalFonts.current.bodyRegularLightAlt // Light Gray placeholder text
            )
        },
        shape = RoundedCornerShape(8.dp), // Rounded corners for input field
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = smallTextLight,
            focusedBorderColor = smallTextLight,
            focusedPlaceholderColor = smallTextLight,
            unfocusedPlaceholderColor = smallTextLight,
            cursorColor = Color.Black
        )
    )
}


@Composable
fun CustomDescriptionTextField(text: String,placeHolder:String,onTextChange: (String) -> Unit){
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp),
        placeholder = {
            androidx.compose.material3.Text(
                placeHolder,
                style = LocalFonts.current.bodyRegularLightAlt // Light Gray placeholder text
            )
        },
        shape = RoundedCornerShape(8.dp), // Rounded corners for input field
        singleLine = false,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = smallTextLight,
            focusedBorderColor = smallTextLight,
            focusedPlaceholderColor = smallTextLight,
            unfocusedPlaceholderColor = smallTextLight,
            cursorColor = Color.Black
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMenuTextField(text: String,placeHolder:String,onSelect: (String) -> Unit){

    val options = listOf("a","b")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded })
        {
            OutlinedTextField(
                value = text,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth().menuAnchor(),
                placeholder = {
                    androidx.compose.material3.Text(
                        placeHolder,
                        style = LocalFonts.current.bodyRegularLightAlt // Light Gray placeholder text
                    )
                },
                shape = RoundedCornerShape(8.dp), // Rounded corners for input field
                singleLine = true, enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = smallTextLight,
                    focusedBorderColor = smallTextLight,
                    disabledBorderColor = smallTextLight,
                    focusedPlaceholderColor = smallTextLight,
                    unfocusedPlaceholderColor = smallTextLight,
                    cursorColor = Color.Black
                )
            )

            ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),expanded = isExpanded, onDismissRequest = {isExpanded=false}) {
                options.forEachIndexed { index, s ->

                    DropdownMenuItem(
                        text = { androidx.compose.material.Text(s) },
                        onClick = {
                            isExpanded = false
                            onSelect(s)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )


                }



            }

        }
    }

}


@Composable
fun TagsField() {
    val tags = remember { mutableStateListOf<String>() } // State to hold the tags
    var textState by remember { mutableStateOf("") } // State for the input text

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    androidx.compose.material3.Text(
                        "Add a tag",
                        style = LocalFonts.current.bodyRegularLightAlt // Light Gray placeholder text
                    )
                },
                shape = RoundedCornerShape(8.dp), // Rounded corners for input field
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = smallTextLight,
                    focusedBorderColor = smallTextLight,
                    focusedPlaceholderColor = smallTextLight,
                    unfocusedPlaceholderColor = smallTextLight,
                    cursorColor = Color.Black
                )
            , keyboardActions = KeyboardActions(onDone = {  val tagText = textState.trim()
                    if (tagText.isNotEmpty() && !tags.contains(tagText)) {
                        tags.add(tagText)
                        textState = "" // Clear input after adding
                    }})
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow( // Display tags horizontally
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tags.toList()) { tag ->
                TagChip(
                    tagName = tag,
                    onRemove = { tags.remove(tag) } // Remove tag from list
                )
                Spacer(modifier = Modifier.width(8.dp)) // Spacing between tags
            }
        }
    }


@Composable
fun TagChip(tagName: String, onRemove: () -> Unit) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(2.dp)).background(
            tagGray
            )

    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = tagName, style = LocalFonts.current.bodySmall) // Tag text
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(R.drawable.cancel), // "x" icon for removal
                contentDescription = "Remove tag",
                modifier = Modifier
                    .size(6.dp)
                    .clickable { onRemove() } // Clickable to trigger removal
            )
        }
    }
}

