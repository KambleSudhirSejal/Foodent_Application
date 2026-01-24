package com.example.foodentapplication.data.models

data class FoodCategoryData(
    val name:String,
    val image:Int
)
data class TabItem(
    val title:String,

)

data class FoodCategory(
    val name:String,
    val imageRes:Int
)

data class CartItem(
    val foodItem :FoodItem,
    val quantity :Int=1
)

