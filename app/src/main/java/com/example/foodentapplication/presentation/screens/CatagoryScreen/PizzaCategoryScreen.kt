package com.example.foodentapplication.presentation.screens.CatagoryScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun PizzaCategoryScreen(navController: NavController) {
    ChineseCategoryCards(navController=navController)
}