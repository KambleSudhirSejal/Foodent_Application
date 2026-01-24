package com.example.foodentapplication.presentation.screens.CatagoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.presentation.components.HomeScreenCards
import com.example.foodentapplication.presentation.viewModel.AddFetchItemViewModel
import com.example.foodentapplication.presentation.viewModel.CartViewModel

@Preview(showBackground = true)
@Composable
fun PreviewCategory(){
    val navController = rememberNavController()
//    AllCategoryScreen(navController, foodListState, viewModelAddFetch, viewModelCart)

}




@Composable

fun AllCategoryScreen(
    navController: NavController,
    foodListState: ResultState<List<FoodItem>>,
    viewModelAddFetch: AddFetchItemViewModel,
    viewModelCart: CartViewModel
) {
    Column(modifier = Modifier.fillMaxSize()){

        Row(
            modifier = Modifier.padding(start=8.dp,bottom =8.dp,top=16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Column{
                Text(
                    text="What Special You want to eat Today !",
                    modifier = Modifier,
                    color= MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier=Modifier.height(20.dp))


            }
        }

        when(foodListState){
            is ResultState.Loading->{

                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()

                }


            }

            is ResultState.Success -> {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    foodListState.data.forEach { food->
                        HomeScreenCards(
                            navController,
                            foodItem = food,
                            viewModelAddFetch,
                            viewModelCart
                        )

                    }

                }

            }

            is ResultState.Error ->{

                Text(
                    text="Failed to load food items",
                    modifier=Modifier.padding(16.dp),
                    color=Color.Red
                )

            }
        }

//        HomeScreenCards(navController = navController)
//        Spacer(modifier = Modifier.height(16.dp))
//        HomeScreenCards(navController = navController)
//        Spacer(modifier = Modifier.height(16.dp))
//        HomeScreenCards(navController = navController)
//        Spacer(modifier = Modifier.height(16.dp))
//        HomeScreenCards(navController = navController)
//        Spacer(modifier = Modifier.height(16.dp))







    }

}