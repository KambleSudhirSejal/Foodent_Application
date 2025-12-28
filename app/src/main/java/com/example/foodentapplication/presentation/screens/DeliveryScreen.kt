package com.example.foodentapplication.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.components.DeliveryScreenSearchBar
import com.example.foodentapplication.presentation.components.FoodCategoryTab
import com.example.foodentapplication.presentation.components.TopAppBarDeliverScreen
import com.example.foodentapplication.presentation.screens.CatagoryScreen.AllCategoryScreen
import com.example.foodentapplication.presentation.screens.CatagoryScreen.ChineseCategoryScreen
import com.example.foodentapplication.presentation.screens.CatagoryScreen.PizzaCategoryScreen

@Preview
@Composable
fun PreviewDelivery(){
    val listState= rememberLazyListState()
    val navController= rememberNavController()
    DeliveryScreen(navController,listState)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DeliveryScreen(navController: NavController, listState: LazyListState) {

    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ){
                TopAppBarDeliverScreen(scrollBehaviour,navController)
                Spacer(modifier = Modifier.height(3.dp))
                DeliveryScreenSearchBar(navController)

            }
        }

    ){paddingValues ->

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 0.dp),
            modifier= Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
                .nestedScroll(scrollBehaviour.nestedScrollConnection)
        ) {
            stickyHeader {
                FoodCategoryTab(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color=Color.White),
                    selectedTabIndex=selectedTabIndex,
                    onTabSelected = {selectedTabIndex=it}

                )

            }

            item{
                AnimatedContent(
                    targetState = selectedTabIndex,
                    transitionSpec = {
                        if(targetState>initialState){
                            slideInHorizontally{ width-> width } + fadeIn() with slideOutHorizontally { width->-width }+ fadeOut()
                        }else{

                            slideInHorizontally{ width-> -width } + fadeIn() with slideOutHorizontally { width->width } + fadeOut()

                        }
                    },
                    label = "SlideTabTransition"


                ) {
                    index->
                    when(index){
                        0-> AllCategoryScreen(navController)
                        1-> PizzaCategoryScreen(navController)
                        2-> ChineseCategoryScreen(navController)
                        else->AllCategoryScreen(navController)
                    }

                }
            }
        }

    }
}


