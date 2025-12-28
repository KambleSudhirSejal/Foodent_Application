package com.example.foodentapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodentapplication.R

@Composable
fun ExploreRow(){
   Column(modifier=Modifier.fillMaxWidth()
       .padding(vertical = 4.dp)
       .background(color= Color.White)){

       Text(
           text="Explore More",
           modifier = Modifier.padding(horizontal = 8.dp,
               vertical = 16.dp),
           fontSize = 16.sp,
           color = Color.Gray

       )

       LazyRow(modifier = Modifier.padding(horizontal = 16.dp))
       {
           item{
               ExploreCard(drawable = R.drawable.price_tag,name="Offers")
               Spacer(modifier=Modifier.width(12.dp))
               ExploreCard(drawable = R.drawable.snack_meal,name="Plan Party")
               Spacer(modifier=Modifier.width(12.dp))
               ExploreCard(drawable = R.drawable.collections,name="Collections")
               Spacer(modifier=Modifier.width(12.dp))
               ExploreCard(drawable = R.drawable.fruits,name="Healthy")
               Spacer(modifier=Modifier.width(12.dp))



           }

       }

   }

}

@Composable
fun ExploreCard(
    drawable:Int,name:String
){

    Card(
        modifier= Modifier.size(width=100.dp,height = 110.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors= CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Icon(
                painter = painterResource(drawable),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier.size(50.dp)

            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center

            )

        }

    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExplorePreviewFun(){
  ExploreRow()
}

