package com.example.uiassessment.viewmodel

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostandfound.utils.UIState
import com.example.uiassessment.models.FoodRequestDTO
import com.example.uiassessment.models.FoodDTO
import com.example.uiassessment.models.FoodResponseDTO
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


    val _getFoodsLiveData : MutableLiveData<UIState<FoodResponseDTO?>> = MutableLiveData(UIState.InitialState())
    val getFoodsLiveData
        get() = _getFoodsLiveData as LiveData<UIState<FoodResponseDTO?>>

    val filteredFoods = SnapshotStateList<FoodDTO>()



    fun filterFoods(query:String) {
        filteredFoods.clear()
        if(query.isEmpty()){
            (getFoodsLiveData.value as UIState.SuccessState<FoodResponseDTO?>).data?.let{
                filteredFoods.addAll(it.data)
            }

        }
        else{
            val tempFiltered = (getFoodsLiveData.value as UIState.SuccessState<FoodResponseDTO?>).data?.let {
                it.data.filter { foodDTO ->
                    foodDTO.name.contains(query,ignoreCase = true)
                }
            }
            tempFiltered?.let {
                filteredFoods.addAll(it)
            }

        }

    }


    fun createFoodRequest(foodDTO: FoodRequestDTO) {
    viewModelScope.launch(Dispatchers.IO){
        try{
            repository.createFoodRequest(foodDTO).collect{
                _createFoodRequestLiveData.postValue(it)
            }
        }
        catch (e:Exception){
            Log.d("ERRORRR", "createFoodRequest: ${e.message}")
                _createFoodRequestLiveData.postValue(UIState.ErrorState("Something went wrong"))
        }
    }



}

    fun getFoods(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                repository.getFoods().collect{
                    _getFoodsLiveData.postValue(it)
                }
            }
            catch (e:Exception){
                Log.d("ERRORRR", "getFoodResponse: ${e.message}")
                _getFoodsLiveData.postValue(UIState.ErrorState("Something went wrong"))
            }
        }
    }





}