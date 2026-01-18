package com.example.foodentapplication.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.common.AppLogger
import com.example.foodentapplication.presentation.screens.AdminLoginScreen
import com.example.foodentapplication.presentation.screens.DeliveryScreen
import com.example.foodentapplication.presentation.screens.FacultyLogin
import com.example.foodentapplication.presentation.screens.FacultyProfileScreen
import com.example.foodentapplication.presentation.screens.FacultySignUp
import com.example.foodentapplication.presentation.screens.SelectRoleScreen
import com.example.foodentapplication.presentation.screens.UserLoginScreen
import com.example.foodentapplication.presentation.screens.UserSignUpScreen

inline fun <reified T> T.route():String=
    T::class.qualifiedName!!


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun App(){
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        AppLogger.showFlow("App Screen","Come in AppScreen ")
    }

    NavHost(
        navController=navController,
        startDestination = Route.SelectRoleScreen.route()
    ){
        composable(Route.SelectRoleScreen.route()){
            SelectRoleScreen(navController)
        }

        composable(Route.UserMainContainer.route()){
            UserMainContainer(navController)

        }

        composable(Route.AdminMainContainer.route()){
            AdminMainContainer(navController)

        }

        composable(Route.FacultyMainContainer.route()){
            FacultyMainContainer(navController)

        }




        composable(Route.UserLoginScreen.route()){
            UserLoginScreen(navController)
        }

        composable(Route.AdminLoginScreen.route()){
            AdminLoginScreen(navController)
        }
        composable(Route.UserSignUpScreen.route()){
            UserSignUpScreen(navController)
        }



        composable(Route.FacultyLogin.route()){
            FacultyLogin(navController)
        }
        composable(Route.FacultySignUp.route()){
            FacultySignUp(navController)
        }

        composable(Route.FacultyProfileScreen.route()) {
            FacultyProfileScreen()

        }







    }

}
