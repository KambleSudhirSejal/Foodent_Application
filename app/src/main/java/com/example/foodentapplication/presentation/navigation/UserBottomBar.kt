package com.example.foodentapplication.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodentapplication.presentation.viewModel.CartViewModel

@Composable
fun UserBottomBar(
    navController: NavController,
    cartViewModel: CartViewModel
) {

    val cartCount by cartViewModel.cartCount.collectAsState()
    val items=listOf(
        UserBottomRoute.Home,
        UserBottomRoute.Cart,
        UserBottomRoute.profile
    )

    NavigationBar {
        val currentRoute = navController
            .currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item->
            NavigationBarItem(
                selected = currentRoute ==item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(UserBottomRoute.Home.route)
                        launchSingleTop=true
                    }
                },
                icon= {

                    if (item == UserBottomRoute.Cart) {
                        BadgedBox(
                            badge = {
                                if (cartCount > 0) {
                                    Badge {
                                        Text(cartCount.toString())
                                    }

                                }
                            }

                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                        }

                    } else {
                        Icon(
                            imageVector = when(item){
                                UserBottomRoute.Home -> Icons.Default.Home
                                UserBottomRoute.Cart -> Icons.Default.ShoppingCart
                                UserBottomRoute.profile -> Icons.Default.Person

                            },
                            contentDescription = null
                        )

                    }
                },


                label={
                    Text(
                        when(item){
                            UserBottomRoute.Home -> "Home"
                            UserBottomRoute.Cart -> "Cart"
                            UserBottomRoute.profile -> "Profile"
                        }

                    )
                }

            )

        }
    }
}