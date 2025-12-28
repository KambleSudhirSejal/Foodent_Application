package com.example.foodentapplication.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.bottombar.AnimatedBottomBar
import com.example.foodentapplication.R
import com.example.foodentapplication.presentation.screens.DeliveryScreen
import com.example.foodentapplication.presentation.screens.DiningScreen
import com.example.foodentapplication.presentation.screens.FinalCheckoutScreen
import com.example.foodentapplication.presentation.screens.LoginScreen
import com.example.foodentapplication.presentation.screens.ParticularCardScreen
import com.example.foodentapplication.presentation.screens.ProfileScreen
import com.example.foodentapplication.presentation.screens.QuickScreen
import com.example.foodentapplication.presentation.screens.SearchBarScreen
import com.example.foodentapplication.presentation.screens.SignUpScreen

data class BottomNavItem(
    val title:String,
    val icon: Painter
)





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    isVisible: Boolean,
    listState: LazyListState
){

    val navController = rememberNavController()

    val navBarStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBarStackEntry?.destination?.route
    var shouldShadowBottomBar by remember { mutableStateOf(false) }

    LaunchedEffect(currentDestination) {
        shouldShadowBottomBar=when(currentDestination){
            Route.DeliveryScreen::class.qualifiedName,
            Route.QuickScreen::class.qualifiedName,
            Route.DiningScreen::class.qualifiedName->true
            else -> false
        }
    }

    var selectedItemIndex by remember {mutableStateOf(0)}

    val BottomNavItems= listOf(
        BottomNavItem(
            title="Deliver",
            icon= painterResource(R.drawable.delivery_cart)
        ),
        BottomNavItem(
            title="Quick",
            icon= painterResource(R.drawable.quick_icon)
        ),
        BottomNavItem(
            title="Dinig",
            icon= painterResource(R.drawable.delivery_cart)
        )

    )

    val selectedColor  = colorResource(R.color.purple_500)
    val bottomBarHeight by animateDpAsState(
        targetValue = if(isVisible) 64.dp else 0.dp

    )

    var startScreen = if(true){
        SubNavigation.LoginSignUpScreen

    }else{
        SubNavigation.MainHomeScreen
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                modifier=Modifier
                    .windowInsetsPadding(
                        WindowInsets.navigationBars.only(WindowInsetsSides.Bottom
                        )
                    )
                    .fillMaxWidth()
                    .height(bottomBarHeight),
                visible=shouldShadowBottomBar,
                enter=fadeIn()+slideInVertically(initialOffsetY = {it}),
                exit= fadeOut()+slideOutVertically(targetOffsetY = {it})
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(bottomBarHeight)
                        .background(Color.White)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .height(3.dp)
                            .background(
                                Color.LightGray.copy(alpha=0.2f)
                            )

                    ){
                        BottomNavItems.forEachIndexed { index,_->
                            Box(
                                modifier = Modifier.padding(horizontal = 10.dp)
                                    .weight(1f)
                                    .height(3.dp).clip(shape= RoundedCornerShape(100.dp))
                                    .background(if(index == selectedItemIndex) selectedColor else Color.Transparent)
                            )

                        }

                    }
                    Surface(
                        modifier = Modifier.fillMaxWidth()
                            .background(Color.White),
                        shadowElevation = 8.dp

                    ){
                        AnimatedBottomBar (
                            containerColor = Color.White,
                            animationSpec = spring(
                                dampingRatio = 1f,
                                stiffness = Spring.StiffnessMediumLow
                            )

                        ){
                           BottomNavItems.forEachIndexed { index, item ->
                               NavigationBarItem(
                                  modifier= Modifier.align(alignment = Alignment.Top),
                                   selected = selectedItemIndex ==index,
                                   onClick = {
                                       selectedItemIndex = index
                                           //when condition should define here to navigate
                                       when(selectedItemIndex){
                                           0-> navController.navigate(Route.DeliveryScreen)
                                           1-> navController.navigate(Route.QuickScreen)
                                           2-> navController.navigate(Route.DiningScreen)
                                       }
                                   },
                                   label= {
                                       if (index == selectedItemIndex) {
                                           Text(
                                               text = item.title,
                                               color = selectedColor,
                                               fontSize = 16.sp
                                           )

                                       } else {
                                           Text(
                                               text = item.title,
                                               color = selectedColor,
                                               fontSize = 16.sp

                                           )

                                       }
                                   },
                                   icon={

                                       Icon(
                                           painter = item.icon,
                                           contentDescription = item.title,
                                           modifier=Modifier.size(24.dp),
                                           tint=if(index==selectedItemIndex){
                                               selectedColor
                                           }else{
                                               Color.Gray
                                           }

                                       )

                                   },
                                   colors = NavigationBarItemDefaults.colors(
                                       indicatorColor = Color.Transparent
                                   )
                               )

                           }

                        }

                    }

                }
            }
        }
    ){paddingValues ->

        Box(
           modifier = Modifier.fillMaxSize()
        ){
            NavHost(navController=navController, startDestination = startScreen){
                navigation<SubNavigation.LoginSignUpScreen>(
                    startDestination = Route.LoginScreen
                ){
                    composable<Route.LoginScreen> {
                        LoginScreen(
                            navController = navController
                        )
                    }

                    composable<Route.SignUpScreen>{
                        SignUpScreen(
                            navController = navController
                        )

                    }
                }


                navigation<SubNavigation.MainHomeScreen>(startDestination = Route.DeliveryScreen){
                    composable<Route.DeliveryScreen> {
                        DeliveryScreen(
                            navController=navController,
                            listState
                        )

                    }

                    composable<Route.QuickScreen>{
                        QuickScreen(
                            navController,listState
                        )
                    }

                    composable<Route.DiningScreen>{
                        DiningScreen(navController,listState)
                    }

                    composable<Route.ProfileScreen>{
                        ProfileScreen(navController,listState)
                    }

                    composable<Route.ParticularCardScreen>{
                        ParticularCardScreen(navController,listState)
                    }

                    composable<Route.FinalCheckOutScreen> {
                        FinalCheckoutScreen(navController, listState)
                    }

                    composable<Route.SearchBarScreen>{
                       SearchBarScreen( navController,modifier = Modifier)
                    }




                }
            }

        }


    }





}