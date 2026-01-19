package com.example.foodentapplication.presentation.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.FoodItem

import com.example.foodentapplication.domain.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFetchItemViewModel @Inject constructor(
    private val repo: Repo
): ViewModel() {

    private val _addFoodState = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val addFoodState = _addFoodState.asStateFlow()

    private val _foodListState = MutableStateFlow<ResultState<List<FoodItem>>>(ResultState.Loading)
    val foodListState = _foodListState.asStateFlow()


    fun addFood(foodItem: FoodItem){
        viewModelScope.launch {
            _addFoodState.value = ResultState.Loading
            _addFoodState.value = repo.addFoodItem(foodItem)
        }
    }

    fun fetchFoods(category:String){
        viewModelScope.launch {
            _foodListState.value = ResultState.Loading
            _foodListState.value = repo.getFoodItem(category)
        }
    }

    fun refreshFood(){
        fetchFoods("all")
    }

    fun resetAddFoodState(){
        _addFoodState.value = ResultState.Loading
    }

    fun deleteFood(foodId: String) {
        viewModelScope.launch {
            repo.deleteFoodItem(foodId)
            refreshFood()
        }
    }

    fun updateFood(foodItem: FoodItem) {
        viewModelScope.launch {
            _addFoodState.value = ResultState.Loading
            _addFoodState.value = repo.updateFoodItem(foodItem)
        }
    }


}