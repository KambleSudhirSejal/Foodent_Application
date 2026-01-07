package com.example.foodentapplication.presentation.screens.CatagoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.components.HomeScreenCards

@Preview(showBackground = true)
@Composable
fun PreviewCategory(){
    val navController = rememberNavController()
    AllCategoryScreen(navController)

}


@Composable
fun ExploreRow(){

}

@Composable

fun AllCategoryScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()){
        ExploreRow()
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

        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        HomeScreenCards(navController = navController)
        Spacer(modifier = Modifier.height(16.dp))







    }

}