package com.example.foodentapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.domain.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepo: CartRepo
): ViewModel() {

    val cartItems = cartRepo.observeCartItems()

    val cartCount: StateFlow<Int> =
        cartItems.map { list ->
            list.sumOf { it.quantity }

        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = 0
            )

    fun addItem(foodItem: FoodItem){
        viewModelScope.launch {
            cartRepo.addToCart(foodItem)
        }
    }

    fun increaseQuantity(foodId:String) {
         viewModelScope.launch {
             cartRepo.increaseQuantity(foodId)
         }
    }

    fun decreaseQuantity(foodId:String){
        viewModelScope.launch {
            cartRepo.decreaseQuantity(foodId)
        }
    }

    fun removeItem(foodId:String){
        viewModelScope.launch {
            cartRepo.removeFromCart(foodId)
        }
    }

    fun clearCart(){
        viewModelScope.launch {
            cartRepo.clearCart()
        }
    }



}