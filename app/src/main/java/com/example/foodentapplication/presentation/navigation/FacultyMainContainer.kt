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
import com.example.foodentapplication.presentation.screens.FacultyProfileScreen
import com.example.foodentapplication.presentation.screens.FinalCheckoutScreen


@Composable
fun FacultyMainContainer(navController: NavController) {
    val bottomNavController= rememberNavController()
    val listState = rememberLazyListState()

    Scaffold(
        bottomBar = {
            FacultyBottomBar(navController=bottomNavController)
        }
    ) { innerPadding->
        NavHost(
            navController=bottomNavController,
            startDestination = FacultyBottomRoute.Menu.route,
            modifier= Modifier.padding(innerPadding)

        ){
            composable(FacultyBottomRoute.Menu.route){
                DeliveryScreen(navController,listState)
            }
            composable(FacultyBottomRoute.Cart.route){
//                FinalCheckoutScreen(navController, cartViewModel)

            }
            composable(FacultyBottomRoute.Profile.route){
                FacultyProfileScreen()

            }

        }

    }
}