package com.example.uiassessment.repository

import android.content.SharedPreferences
import com.example.lostandfound.retrofit.NetworkAPIService
import com.example.lostandfound.utils.UIState
import com.example.uiassessment.models.FoodDTO
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.awaitResponse
import javax.inject.Inject

class MainRepository@Inject constructor(val networkAPIService: NetworkAPIService) {

fun createFoodRequest(foodDTO: FoodDTO)= flow<UIState<Void?>> {
    emit(UIState.LoadingState())
    val name = foodDTO.name.toRequestBody("application/json".toMediaTypeOrNull())
    val description = foodDTO.description.toRequestBody("application/json".toMediaTypeOrNull())
    val categoryId = foodDTO.categoryId.toRequestBody("application/json".toMediaTypeOrNull())
    val calories = foodDTO.calories.toRequestBody("application/json".toMediaTypeOrNull())
    val imagePartList = arrayListOf<MultipartBody.Part>()
    val tags = arrayListOf<RequestBody>()


    for(tag in foodDTO.tags){

        val tagBody = tag.toRequestBody("application/json".toMediaTypeOrNull())
        tags.add(tagBody)


    }

    for(image in foodDTO.images){
        val imageBody  = image.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("images[]",image.name,imageBody)
        imagePartList.add(imagePart)
    }

    val response=networkAPIService.postAddItem(images = imagePartList,
        name, description, categoryId, calories, tags).awaitResponse()

    if(response.isSuccessful){
        emit(UIState.SuccessState(null))
    }else{
        emit(UIState.ErrorState("Something went wrong"))
    }

}



}