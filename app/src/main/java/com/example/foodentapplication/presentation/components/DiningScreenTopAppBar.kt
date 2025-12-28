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
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
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
fun PreviewDiningTopAppBar(){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController= rememberNavController()
    TopAppBarDiningScreen(
        scrollBehavior,navController
    )

}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDiningScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {

    val collapsedFraction = scrollBehavior.state.collapsedFraction

    //Content color transition from white (on deep blue ) to black (on light background)
    val contentColor = lerp(
        Color.White,
        Color.Transparent,
        collapsedFraction
    )

    //apply alpha to fade content when scrolling

    val contentAlpha = 1f-collapsedFraction

    TopAppBar (
        title = {
            Column(modifier = Modifier.alpha(contentAlpha)){
                Row(
                    modifier = Modifier.fillMaxWidth().height(22.dp)

                ){
                    Text(
                        text="Home",
                        color=contentColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )
                    Icon(
                        painter = painterResource(R.drawable.down_arrow),
                        modifier = Modifier.size(30.dp).padding(top=6.dp),
                        tint=contentColor,
                        contentDescription = "Down arrow"

                    )

                }

                Text(
                    text="New Indora Republican Nagar",
                    fontWeight= FontWeight.SemiBold,
                    color=contentColor,
                    fontSize = 15.sp
                )

            }
        },

      navigationIcon = {
          Icon(
              painter=painterResource(R.drawable.locationdiningscreen),
              modifier =Modifier.size(30.dp),
              tint=contentColor,
              contentDescription = "location"
          )

      },
        actions={
           IconButton(onClick = {

           }){
               Icon(
                   painter=painterResource(R.drawable.profile),
                   contentDescription = "Profile",
                   tint = contentColor
               )
           }


        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        scrollBehavior=scrollBehavior,
        modifier=Modifier.fillMaxWidth()
    )



}