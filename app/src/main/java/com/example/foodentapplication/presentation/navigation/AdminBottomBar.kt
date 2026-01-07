package com.example.foodentapplication.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AdminBottomBar(
    navController: NavController
) {
    val items=listOf(
        AdminBottomRoute.Menu,
        AdminBottomRoute.Order,
        AdminBottomRoute.Profile
    )

    NavigationBar {
        val currentRoute = navController
            .currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(AdminBottomRoute.Menu.route)
                        launchSingleTop=true
                    }
                },
                icon={
                    Icon(
                    imageVector=when(item){
                        AdminBottomRoute.Menu -> Icons.Default.Menu
                        AdminBottomRoute.Order->Icons.Default.Fastfood
                        AdminBottomRoute.Profile->Icons.Default.Person

                    },
                        contentDescription = null
                    )

                },
                label={
                    Text(
                        when(item){
                            AdminBottomRoute.Menu -> "Menu"
                            AdminBottomRoute.Order -> "Order"
                            AdminBottomRoute.Profile -> "Profile"
                        }

                    )
                }

            )

        }
    }
}