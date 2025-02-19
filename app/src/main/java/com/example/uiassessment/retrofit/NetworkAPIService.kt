package com.example.lostandfound.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkAPIService {



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