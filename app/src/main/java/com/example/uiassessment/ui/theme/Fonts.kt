package com.example.uiassessment.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

data class Fonts(
    val titleBold:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Bold,
        fontSize = 16.sp, color = titleBlack),
    val title:TextStyle= TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Medium,
        fontSize = 16.sp, color = titleBlack),
    val bodySmall:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 12.sp, color = titleBlack, ),
    val bodySmallLight:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 12.sp, color = smallTextLight , ),

    val bodySmallSecondary:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 12.sp, color = secondaryTextLight, ),


    val bodyRegular:TextStyle =TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 14.sp, color = titleBlack, ),
    val bodyRegularLight:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 14.sp, color = textLight , ),
    val navText:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 12.sp, color = textLight , ),
    val navHighlightedText:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Bold,
        fontSize = 12.sp, color = highlightBlue , ),

    val optionText:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Normal,
        fontSize = 14.sp, color = optionTextLight , ),
    val optionTextHighlighted:TextStyle = TextStyle(fontFamily = Satoshi, fontWeight = FontWeight.Medium,
        fontSize = 14.sp, color = Color.White)


)

val LocalFonts = compositionLocalOf { Fonts() }