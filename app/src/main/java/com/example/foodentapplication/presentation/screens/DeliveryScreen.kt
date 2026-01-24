package com.example.foodentapplication.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.components.DeliveryScreenSearchBar
import com.example.foodentapplication.presentation.components.TopAppBarDeliverScreen
import com.example.foodentapplication.presentation.screens.CatagoryScreen.AllCategoryScreen
import com.example.foodentapplication.presentation.viewModel.AddFetchItemViewModel
import com.example.foodentapplication.presentation.viewModel.CartViewModel

@Preview
@Composable
fun PreviewDelivery(){
    val listState= rememberLazyListState()
    val navController= rememberNavController()
    DeliveryScreen(navController,listState)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DeliveryScreen(navController: NavController,
                   listState: LazyListState,
viewModelAddFetch : AddFetchItemViewModel = hiltViewModel(),
                   viewModelCart: CartViewModel =hiltViewModel()
) {

   val foodListState by viewModelAddFetch.foodListState.collectAsState()

    LaunchedEffect(Unit){
                   viewModelAddFetch.fetchFoods("all")
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
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .nestedScroll(scrollBehaviour.nestedScrollConnection)
        ) {


            item{


              AllCategoryScreen(navController,foodListState,viewModelAddFetch,viewModelCart)
            }
        }

    }
}


