package com.example.uiassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.lostandfound.utils.UIState
import com.example.uiassessment.models.FoodDTO
import com.example.uiassessment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) : ViewModel() {

val _createFoodRequestLiveData : MutableLiveData<UIState<Void?>> = MutableLiveData(UIState.InitialState())
    val createFoodRequestLiveData
        get() = _createFoodRequestLiveData as LiveData<UIState<Void?>>

fun createFoodRequest(foodDTO: FoodDTO) {
    viewModelScope.launch(Dispatchers.IO){
        try{
            repository.createFoodRequest(foodDTO).collect{
                _createFoodRequestLiveData.postValue(it)
            }
        }
        catch (e:Exception){
            Log.d("ERRORRR", "createFoodRequest: ${e.message}")
                _createFoodRequestLiveData.postValue(UIState.ErrorState("Crashed"))
        }
    }



}





}