package com.example.foodentapplication.presentation.utils

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.R
import com.example.foodentapplication.presentation.navigation.Route

@Preview
@Composable
fun PreviewBottomSheet(){
    val navController= rememberNavController()
    val onDismiss={}
    BottomSheetToAddProducts(onDismiss={},navController)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetToAddProducts(
    onDismiss:()->Unit,
    navController: NavController


) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var text by remember { mutableStateOf("") }
    var count by remember {mutableStateOf(1)}

    ModalBottomSheet(
        modifier = Modifier.fillMaxWidth()
            .systemBarsPadding(),
        sheetState = sheetState,
        dragHandle = {},
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onDismissRequest = {onDismiss()}

    ){
        Scaffold(
            bottomBar = {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                    , verticalAlignment = Alignment.Bottom

                ){
                    //counter with +/- buttons
                    Row(
                        modifier = Modifier.border(
                            1.dp,
                            color= colorResource(R.color.purple_500),
                            RoundedCornerShape(4.dp)
                        )
                            .height(40.dp)
                            .background(colorResource(R.color.purple_500)),
                        verticalAlignment = Alignment.CenterVertically

                    ){
                        //minus button
                        Box(
                            modifier= Modifier.width(40.dp)
                                .fillMaxWidth()
                                .clickable{count--},
                            contentAlignment = Alignment.Center

                        ){
                            Text(
                                text="-",
                                fontSize=20.sp,
                                color=colorResource(R.color.purple_500)

                            )
                        }
                          //counter value
                        Box(
                            modifier = Modifier.width(40.dp)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center

                        ){
                            Text(
                                text=count.toString(),
                                fontSize = 16.sp,
                                color=Color.Black
                            )
                        }

                        //plus button

                        Box(
                            modifier= Modifier.width(40.dp)
                                .fillMaxWidth()
                                .clickable{count++},
                            contentAlignment = Alignment.Center

                        ){
                            Text(
                                text="+",
                                fontSize=20.sp,
                                color=colorResource(R.color.purple_500)

                            )
                        }



                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //Additem button

                    Button(onClick = {
                        navController.navigate(Route.FinalCheckOutScreen
                        )
                    },
                        modifier= Modifier.height(40.dp)
                            .weight(1f),
                        colors= ButtonDefaults.buttonColors(colorResource(R.color.purple_500)),
                        shape=RoundedCornerShape(4.dp)
                    ){
                        Text(
                            text="Add item Rs 234",
                            color=Color.White,
                            fontSize = 16.sp
                        )

                    }



                }
            }
        ) { paddingValues ->
            Column(
                modifier= Modifier.fillMaxWidth().padding(horizontal =4.dp)
                    .background(color=Color.Transparent)
            ){
                Card(
                    modifier=Modifier.fillMaxWidth()
                        .background(color=Color.Transparent)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    shape=RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )

                ){

                    Column(
                        modifier=Modifier.fillMaxWidth()
                            .background(Color.White)
                    ){
                        Box(
                            modifier= Modifier.fillMaxWidth()
                                .height(200.dp)
                        ){
                            Image(
                                painter = painterResource(R.drawable.brick_oven_pizza
                                ),
                                contentDescription = "Location background",
                                modifier= Modifier.fillMaxSize()
                                    .padding(12.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop

                            )

                        }

                        Spacer(modifier=Modifier.height(10.dp))

                        Row(
                            modifier=Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Row {
                                Icon(
                                    painter=painterResource(id=R.drawable.veg_icon),
                                    contentDescription = "veg",
                                    modifier=Modifier.size(18.dp),
                                    tint=Color.Unspecified
                                )
                            }

                            Spacer(modifier=Modifier.height(8.dp))

                            Row(
                                modifier=Modifier
                                    .background(
                                    color=Color(0xFFF9F0E6)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center

                            ){
                                Text(
                                    text="BestSeller",
                                    color=Color(0xFFE67E22),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                        Spacer(modifier= Modifier.height(10.dp))
                        Column(
                            modifier= Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ){
                            Row(
                                modifier=Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.Top
                            ){

                                Text(
                                    text="Brick oven Pizza",
                                    color=Color.Black,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier=Modifier.weight(1f)
                                )

                                Row{
                                    Icon(
                                        imageVector = Icons.Outlined.Share,
                                        contentDescription = "Share",
                                        tint= Color.Gray,
                                        modifier=Modifier.padding(horizontal = 8.dp)

                                        )

                                    Icon(
                                        painter = painterResource(R.drawable.outline_bookmark_24),
                                        contentDescription = "Share",
                                        tint= Color.Gray,


                                    )

                                }


                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier= Modifier.padding(vertical = 8.dp)
                            ){
                                    repeat(5){index->

                                        Icon(
                                            imageVector = Icons.Outlined.Share,
                                            contentDescription = "null",
                                            tint= Color(0xFFFFC107),
                                            modifier=Modifier.size(16.dp)

                                        )

                                    }
                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text="(2110)",
                                    color=Color.Gray,
                                    fontSize = 12.sp
                                )


                            }

                            Text(
                                text="[Chef's Special]",
                                color=Color.Gray,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }

                        }

                }

                Card(
                    modifier=Modifier.fillMaxWidth()
                        .padding(8.dp),
                    shape=RoundedCornerShape(16.dp),
                    colors= CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border= BorderStroke(1.dp,Color(0xFFE0E0E0))

                ){

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ){
                        Row(
                            modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text="Add a cooking request (optional) "
                                        ,
                                color=Color(0xFF424242),
                                fontSize = 16.sp,
                             fontWeight = FontWeight.Medium
                            )

                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Infomation",
                                tint= Color(0xFFBDBDBD),
                                modifier=Modifier.size(20.dp)

                            )


                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        TextField(
                            value = text,
                            onValueChange = {text=it},
                            placeholder = {
                                Text(
                                    "e.g Don't make it too spicy",
                                    color=Color(0xFFBDBDBD),
                                    fontSize = 14.sp

                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(top=8.dp),
                            colors= TextFieldDefaults.textFieldColors(
                                focusedLabelColor = Color(0xFFF5F5F5),
                                unfocusedLabelColor =Color(0xFFF5F5F5),
                                disabledLabelColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent

                            ),
                            shape=RoundedCornerShape(8.dp)
                        )

                    }

                }

            }



        }

    }
}