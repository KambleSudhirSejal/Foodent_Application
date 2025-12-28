package com.example.foodentapplication.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.R
import com.example.foodentapplication.presentation.components.DiningScreenContent
import com.example.foodentapplication.presentation.components.DiningSearchBar
import com.example.foodentapplication.presentation.components.DiningSlideComponent
import com.example.foodentapplication.presentation.components.RestaurantPromotion
import com.example.foodentapplication.presentation.components.TopAppBarDiningScreen


@Preview
@Composable
fun PreviewDining(){
    val navController= rememberNavController()
    val listState= rememberLazyListState();
    DiningScreen(navController,listState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiningScreen(navController: NavController, listState: LazyListState) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val sampleProtions = listOf(
        RestaurantPromotion(
            imageRes = R.drawable.restaurant1,
            name="Lezztli",
            tagline="Experience the finer things",
            discount="Flat 15% OFF"
        ),
        RestaurantPromotion(
            imageRes = R.drawable.restaurant2,
            name="Spice Garder",
            tagline="Authentic lavors of Indias",
            discount="Buy 1 Get 1 free"
        ),
        RestaurantPromotion(
            imageRes = R.drawable.restaurant3,
            name="Lezztli",
            tagline="Experience the finer things",
            discount="Dlat 15% OFF"
        ),


    )

    Scaffold(
        containerColor = Color.Transparent, topBar = {
            Column(
                modifier=Modifier.fillMaxSize()
            ){
                TopAppBarDiningScreen(scrollBehavior,navController)
                Spacer(modifier=Modifier.height(3.dp))
                DiningSearchBar(navController)
            }

        }
    ) { innerPadding ->
        LazyColumn(state=listState,
            contentPadding = PaddingValues(vertical = 0.dp),
            modifier = Modifier.fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            item{
                Box(
                    modifier=Modifier.fillMaxWidth()
                ){
                    Image(
                        painter= painterResource(R.drawable.diningbanner),
                        modifier= Modifier.fillMaxWidth()
                            .clip(
                                shape= RoundedCornerShape(
                                    bottomStart = 15.dp,
                                    bottomEnd = 15.dp
                                )
                            ),
                        contentDescription = "Dining Screen Banner",


                    )
                }
            }
            item{
                DiningSlideComponent(
                    promotions = sampleProtions,
                    modifier=Modifier.padding(horizontal=16.dp)
                )
                DiningScreenContent()
            }

        }


    }
}