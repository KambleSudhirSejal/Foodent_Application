package com.example.foodentapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.screens.DeliveryScreen
import com.example.foodentapplication.presentation.screens.FinalCheckoutScreen
import com.example.foodentapplication.presentation.screens.UserCartScreen
import com.example.foodentapplication.presentation.screens.UserHomeScreen
import com.example.foodentapplication.presentation.screens.UserProfileScreen

@Composable
fun UserMainContainer(
    navController: NavController
) {

    val bottomNavController = rememberNavController()
    val listState= rememberLazyListState()

    Scaffold(
        bottomBar = {
            UserBottomBar(navController=bottomNavController)
        }

    ) { innerPadding->

        NavHost(
            navController=bottomNavController,
            startDestination= UserBottomRoute.Home.route,
            modifier= Modifier.padding(innerPadding)
        ){
            composable(UserBottomRoute.Home.route){
                DeliveryScreen(navController,listState)

            }
            composable(UserBottomRoute.Cart.route){
                FinalCheckoutScreen(navController,listState)

            }
            composable(UserBottomRoute.profile.route){
                UserProfileScreen()

            }
        }


    }
}