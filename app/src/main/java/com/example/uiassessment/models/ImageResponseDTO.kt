package com.example.uiassessment.models

import com.google.gson.annotations.SerializedName

class ImageResponseDTO(val id:Int,
    @SerializedName("image_url")
    val image:String)
{
}