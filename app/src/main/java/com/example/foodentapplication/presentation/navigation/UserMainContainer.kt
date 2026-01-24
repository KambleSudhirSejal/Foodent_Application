package com.example.foodentapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.screens.DeliveryScreen
import com.example.foodentapplication.presentation.screens.FinalCheckoutScreen
import com.example.foodentapplication.presentation.screens.UserProfileScreen
import com.example.foodentapplication.presentation.viewModel.CartViewModel

@Composable
fun UserMainContainer(
    navController: NavController
) {

    val bottomNavController = rememberNavController()
    val listState= rememberLazyListState()

    val viewModel: CartViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            UserBottomBar(navController=bottomNavController,viewModel)
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
                FinalCheckoutScreen(navController,viewModel)

            }
            composable(UserBottomRoute.profile.route){
                UserProfileScreen()

            }
        }


    }
}