package com.example.lostandfound.retrofit

import com.example.uiassessment.models.FoodDTO
import com.example.uiassessment.models.FoodResponseDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkAPIService {


    @GET("api/foods")
    fun getFoods(): Call<FoodResponseDTO>

    @Multipart
    @POST("api/foods")
    fun postAddItem(
                    @Part images : List<MultipartBody.Part>,
                    @Part("name") name: RequestBody,
                    @Part("description") description: RequestBody,
                    @Part("category_id") categoryId: RequestBody,
                    @Part("calories") calories: RequestBody,
                    @Part("tags[]") tags:ArrayList<RequestBody>):Call<Any>


}