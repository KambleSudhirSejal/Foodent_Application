package com.example.foodentapplication.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun FacultyBottomBar(
    navController: NavController
) {

    val items=listOf(
        FacultyBottomRoute.Menu,
        FacultyBottomRoute.Cart,
        FacultyBottomRoute.Profile
    )

    NavigationBar {
        val currentRoute = navController
            .currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(FacultyBottomRoute.Menu.route)
                        launchSingleTop=true
                    }
                },
                icon={
                    Icon(
                        imageVector=when(item){
                            FacultyBottomRoute.Menu -> Icons.Default.Menu
                            FacultyBottomRoute.Cart->Icons.Default.ShoppingCart
                            FacultyBottomRoute.Profile->Icons.Default.Person

                        },
                        contentDescription = null
                    )

                },
                label={
                    Text(
                        when(item){
                            FacultyBottomRoute.Menu -> "Menu"
                            FacultyBottomRoute.Cart -> "Cart"
                            FacultyBottomRoute.Profile -> "Profile"
                        }

                    )
                }

            )

        }


    }
}