package com.example.uiassessment.models

import com.google.gson.annotations.SerializedName
import java.io.File

class FoodDTO(
    val name: String,
    val description: String,
    @SerializedName("category_id")
    val categoryId: String, // Or Int if you prefer and the API expects integer
    val calories: String,    // Or Int if you prefer and the API expects integer
    val tags: List<String>, // List of tag IDs as Strings
    val images: List<File> // List of image files to upload. Use File? to allow for null if no image.
){

}