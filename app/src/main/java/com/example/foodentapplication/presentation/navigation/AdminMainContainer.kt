package com.example.foodentapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.screens.AdminMenuScreen
import com.example.foodentapplication.presentation.screens.AdminOrderScreen
import com.example.foodentapplication.presentation.screens.AdminProfileScreen



@Composable
fun AdminMainContainer(
    navController: NavController
){

    val bottomNavController= rememberNavController()

    Scaffold(
        bottomBar = {
            AdminBottomBar(navController=bottomNavController)
        }
    ) { innerPadding->
        NavHost(
            navController=bottomNavController,
            startDestination = AdminBottomRoute.Menu.route,
            modifier= Modifier.padding(innerPadding)

        ){
            composable(AdminBottomRoute.Menu.route){
                AdminMenuScreen(navController)

            }
            composable(AdminBottomRoute.Order.route){
                AdminOrderScreen()

            }
            composable(AdminBottomRoute.Profile.route){
                AdminProfileScreen()

            }

        }

    }
}