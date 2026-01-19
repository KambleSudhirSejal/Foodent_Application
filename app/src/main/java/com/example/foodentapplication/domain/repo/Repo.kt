package com.example.foodentapplication.domain.repo

import android.net.Uri
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.data.models.UserData
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repo {
  suspend  fun loginWithEmailAndPassword(userData: UserData): ResultState<String>
  suspend  fun registerWithEmailAndPassword(userData: UserData): ResultState<UserData>

  suspend fun addFoodItem(foodItem: FoodItem) : ResultState<String>
  suspend fun getFoodItem(category:String): ResultState<List<FoodItem>>

  suspend fun updateFoodItem(foodItem: FoodItem): ResultState<String>

  suspend fun deleteFoodItem(foodId:String) : ResultState<String>

}