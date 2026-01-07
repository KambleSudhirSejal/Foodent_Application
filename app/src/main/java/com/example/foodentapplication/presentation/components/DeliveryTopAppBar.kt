package com.example.foodentapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar(){
    val scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior()
    val navController= rememberNavController()
    TopAppBarDeliverScreen(scrollBehavior,navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDeliverScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {

    TopAppBar(
        title={
            Column{
                Row(
                    modifier=Modifier.fillMaxWidth()
                        .height(22.dp)
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.background)

                ) {
                    Text(
                        text = "Sejal kamble",
                        fontSize = 20.sp,
                        color =Color.White,
                        fontWeight = FontWeight.Bold

                    )
                    Icon(
                        painter = painterResource(R.drawable.down_arrow),
                        modifier = Modifier.padding(top = 6.dp).size(30.dp),
                        tint = Color.DarkGray,
                        contentDescription = "Down Array",


                        )
                }
                    Text(
                        text="Kamblesejal277@gmail.com",
                        fontWeight = FontWeight.SemiBold,
                        color=Color.Gray,
                        fontSize = 15.sp
                    )


            }
        },
        navigationIcon ={
            Icon(
                imageVector  = Icons.Default.School,
                modifier = Modifier.size(35.dp),
                tint=Color.Red,
                contentDescription = "Location"
            )


        },
        actions = {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clickable{
                        //to navigate
                    }
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center

            ){
                Text("S",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color=Color.Blue)
            }

        },
        colors= TopAppBarDefaults.topAppBarColors(Color.Transparent),
        scrollBehavior = scrollBehavior,
        modifier=Modifier.padding(horizontal=4.dp)

    )


}