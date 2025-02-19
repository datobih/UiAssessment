package com.example.uiassessment.models

import com.google.gson.annotations.SerializedName

class FoodDTO(val name: String,
              val description: String,
              @SerializedName("category_id")
                          val categoryId: String, // Or Int if you prefer and the API expects integer
              val calories: String,    // Or Int if you prefer and the API expects integer
              val foodTags: List<String>, // List of tag IDs as Strings
              val foodImages: List<ImageResponseDTO> )