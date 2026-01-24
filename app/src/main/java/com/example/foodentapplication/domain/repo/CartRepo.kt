package com.example.foodentapplication.domain.repo

import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.CartItem
import com.example.foodentapplication.data.models.FoodItem
import kotlinx.coroutines.flow.Flow

interface CartRepo {

    fun observeCartItems() : Flow<List<CartItem>>

    suspend fun addToCart(foodItem: FoodItem): ResultState<String>

    suspend fun removeFromCart(foodId:String): ResultState<String>

    suspend fun clearCart(): ResultState<String>
}