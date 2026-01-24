package com.example.foodentapplication.data.repoImpl

import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.CartItem
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.domain.repo.CartRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CartItemImpl @Inject
constructor() : CartRepo{

    private val cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    override fun observeCartItems(): Flow<List<CartItem>> = cartItems

    override suspend fun addToCart(foodItem: FoodItem): ResultState<String> {
       val current = cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.foodItem.id == foodItem.id }

        if(index >= 0){
            val item = current[index]
            current[index] = item.copy(quantity =item.quantity+1)
        }else{
            current.add(CartItem(foodItem))
        }

        cartItems.value = current
        return ResultState.Success("Items added to cart ")
    }

    override suspend fun removeFromCart(foodId: String): ResultState<String> {
        cartItems.value = cartItems.value.filterNot{
            it.foodItem.id == foodId
        }

        return ResultState.Success("Item removed")

    }

    override suspend fun clearCart(): ResultState<String> {
        cartItems.value = emptyList()
        return ResultState.Success("Cart cleared")
    }
}