package com.example.foodentapplication.presentation.screens

import android.R.attr.navigationIcon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.R
import com.example.foodentapplication.presentation.utils.OrderPlaceDialog

@Preview
@Composable
fun PreviewFinalCheckout(){
    val navController= rememberNavController()
    val listState= rememberLazyListState()
    FinalCheckoutScreen(navController,listState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalCheckoutScreen(navController: NavController, listState: LazyListState) {
    var showOrderDialog  by remember { mutableStateOf(false) }

    var name by remember {mutableStateOf("Rominus Pizza And Burger")}
    var time by remember {mutableStateOf("37 mins")}
    var productName by remember {mutableStateOf( "Peri Peri Burger")}
    var price by remember {mutableStateOf("Rs 249")}
    var newPrice by remember {mutableStateOf("RS 290")}

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(WindowInsets.navigationBars.only(
                WindowInsetsSides.Bottom
            ).asPaddingValues()
            ),
        topBar = {
            TopAppBar(
                title = {
                    Column{
                        Text(text = name,
                            fontSize = 13.sp,
                            color=Color.DarkGray
                        )
                        Row(
                            modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text="$time to Home",color=Color.Gray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            VerticalDivider(modifier= Modifier
                                .padding(horizontal = 4.dp)
                                .padding(top=10.dp,bottom=12.dp)
                            )
                            Text(text="New Indora Republican Nagar , Nagpur .....",
                                fontSize = 12.sp,
                                color=Color.DarkGray
                            )
                            Icon(
                                painter= painterResource(R.drawable.down_arrow),
                                modifier=Modifier.padding(horizontal = 3.dp)
                                    .size(14.dp),
                                tint=Color.DarkGray,
                                contentDescription = "down arrow"
                            )

                        }


                    }

                },
                navigationIcon={
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(painterResource(R.drawable.arrowback),
                            contentDescription = "Back Navigation",
                            modifier = Modifier.size(22.dp),
                            tint=Color.DarkGray)
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ){
                        Icon(painter=painterResource(R.drawable.share),
                            contentDescription = "Back Navigation",
                            modifier=Modifier.size(18.dp),
                            tint=Color.DarkGray)

                    }

                },
                colors= TopAppBarDefaults.topAppBarColors(Color.White)


            )
        },
        bottomBar={
            Column(
              modifier= Modifier.fillMaxWidth()
                  .background(Color.White)
            ){
                Button(
                    onClick = {
                        showOrderDialog=true

                    },
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(horizontal=16.dp, vertical = 20.dp),
                    colors= ButtonDefaults.buttonColors(containerColor = colorResource(R.color.purple_500)),
                    shape= RoundedCornerShape(8.dp)
                ){
                    Box(
                        modifier=Modifier.fillMaxWidth()
                    ){
                        Text(
                            text="Place Order",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier=Modifier.align(Alignment.Center)
                        )
                        Icon(
                            painter =painterResource(R.drawable.baseline_arrow_right_24),
                            contentDescription = "Next",
                            modifier=Modifier.align(Alignment.CenterEnd).size(35.dp)
                        )
                    }


                }

            }
            if(showOrderDialog){
                OrderPlaceDialog(
                    onDismiss = {
                        showOrderDialog=false
                    },
                    onConfirm = {
                        showOrderDialog=false
                            //Add additional configiuration large logc if needed
                    }
                )
            }
        }


    ) { innerPadding ->


        LazyColumn(
            contentPadding = PaddingValues(vertical = 0.dp),
            modifier=Modifier.fillMaxSize()
                .padding(innerPadding)
        ){
            item{
                ProductCard(productName,price)
                CouponCard()
                Spacer(modifier=Modifier.height(16.dp))
                AddressAndBillCard(
                    time,newPrice
                )
                Column(
                    modifier=Modifier
                        .fillMaxWidth()
                        .padding(horizontal=8.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.Center
                    ){
                    Card(
                        modifier=Modifier
                            .fillMaxWidth(),
                        colors= CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ){
                        Box(
                            modifier=Modifier.fillMaxSize()
                        ){
                            Image(
                                painter=painterResource(id=R.drawable.donationbanner),
                                contentDescription = "Restaurants near me ",
                                modifier=Modifier.fillMaxSize()
                            )

                        }

                    }

                    Spacer(modifier=Modifier.height(16.dp))
                    Text(
                        text="CANCELLATION POLICY",
                        modifier=Modifier.padding(horizontal=4.dp),
                        color=Color.DarkGray
                    )
                    Text(
                        text="Help us reduce food waste by avoiding cancellation.The payment is non refundable after payment ",
                        modifier=Modifier.padding(horizontal = 4.dp),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        color=Color.Gray
                    )

                }

            }

        }

    }


}

@Composable
fun ProductCard(productName: String, price: String) {
    var quantity by remember {mutableStateOf(1)}
    Card(
        modifier=Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.purple_200))
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ){
            Icon(
                painter =painterResource(R.drawable.goldicon1),
                contentDescription = "Gold",
                tint=Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Column(
               modifier= Modifier.weight(1f)
                   .padding(horizontal = 20.dp)
            ){
                Text(
                    text="Get gold for 3 months",
                    color=Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp

                )
                Spacer(modifier=Modifier.height(2.dp))
                Text(
                    text="Unlimited free deliveries & more benefits",
                    color=Color.Gray,

                    fontSize = 12.sp

                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text="Learn more",
                        color=Color.DarkGray,

                        fontSize = 14.sp

                    )
                    Icon(
                        painter =painterResource(R.drawable.baseline_arrow_right_24),
                        contentDescription = "Learn more",
                        tint=colorResource(R.color.purple_500),

                    )
                }

            }
            Column(
                horizontalAlignment = Alignment.End
            ){
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.height(32.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor =colorResource(R.color.purple_500) ),
                    border = BorderStroke(1.dp,colorResource(R.color.purple_500))
                ) {
                    Text("ADD")
                }
                Spacer(modifier=Modifier.height(2.dp))
                Text(
                    text="Rs 30",
                    color=Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )

            }

        }
        //product details
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ){
            Icon(
                painter=painterResource(R.drawable.veg_icon),
                contentDescription = "Veg",
                tint=Color.Unspecified,
                modifier=Modifier.padding(top=4.dp).size(16.dp)
            )

            Column(
                modifier=Modifier
                    .padding(horizontal = 12.dp)
            ){
                Text(
                    text=productName,
                    color=Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text=price,
                    color=Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

            }
            Spacer(modifier=Modifier.weight(1f))
            //card to handle quatity
            Column(
                horizontalAlignment = Alignment.End
            ){
                Card(
                    modifier=Modifier.size(width = 75.dp, height = 26.dp),
                    colors = CardDefaults.cardColors(colorResource(R.color.purple_200)),
                    border=BorderStroke(width = 1.dp,colorResource(R.color.purple_200)),
                    shape = RoundedCornerShape(8.dp)
                ){
                    Row(
                        modifier=Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Icon(
                            painter=painterResource(R.drawable.outline_check_indeterminate_small_24),
                            modifier=Modifier.padding(2.dp).clickable{
                                quantity--
                            },
                            tint=colorResource(R.color.purple_500),
                            contentDescription = null
                        )
                        Text(
                            text=quantity.toString(),
                            color=Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painter=painterResource(R.drawable.baseline_add_24),
                            modifier=Modifier.padding(2.dp).size(18.dp).clickable{
                                quantity++
                            },
                            tint=colorResource(R.color.purple_500),
                            contentDescription = null
                        )


                    }

                }
                Text(text=price)
                Text(
                    text=price,
                    color=Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(2.dp)
                )

            }
        }
        Spacer(modifier=Modifier.height(8.dp))
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add more",
                tint=Color(0xFFE91E63),
                modifier=Modifier.size(18.dp)
            )
            Text(
                text="Add items",
                color=colorResource(R.color.purple_200),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start=4.dp)
            )

        }

        HorizontalDivider(
            modifier=Modifier.padding(vertical = 8.dp,
                horizontal = 8.dp),
            color=colorResource(R.color.purple_200)
        )

        //Lazy row of product card
        LazyRow{
            item{
                Row(
                    modifier= Modifier.fillMaxSize()
                        .padding(horizontal=16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    OutlinedButton(
                        onClick = {},
                        shape=RoundedCornerShape(8.dp),
                        colors=ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        border = BorderStroke(1.dp,Color.LightGray),
                        modifier = Modifier.padding(end=8.dp)
                    ) {
                        Icon(
                            painter=painterResource(R.drawable.cutlery),

                            contentDescription = "Cutlery",
                            tint=Color.Black,
                            modifier=Modifier.size(16.dp)
                        )
                        Text(
                            text="Don't send cutleru",
                            fontSize = 14.sp,
                            modifier=Modifier.padding(start=4.dp)
                        )
                    }

                    //2nd
                    OutlinedButton(
                        onClick = {},
                        shape=RoundedCornerShape(8.dp),
                        colors=ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        border = BorderStroke(1.dp,Color.LightGray)

                    ) {
                        Icon(
                            painter=painterResource(R.drawable.notes),

                            contentDescription = "Note",
                            tint=Color.Black,
                            modifier=Modifier.size(16.dp)
                        )
                        Text(
                            text="Add a note for  the restaurant",
                            fontSize = 14.sp,
                            modifier=Modifier.padding(start=4.dp)
                        )
                    }

                }
            }
        }




    }

}

@Composable
fun AddressAndBillCard(time: String, newPrice: String) {
    //Delivery Inforamation

    Card(
        modifier=Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        //Delivery time
        Row(
            modifier=Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ){
            Icon(
                painter=painterResource(R.drawable.timer),
                contentDescription = "Delivery Time",
                tint=colorResource(R.color.purple_200),
                modifier=Modifier.size(16.dp)
            )

            Column(
                modifier= Modifier
                    .weight(1f)
                    .padding(start=8.dp),

            ){
                Text(
                    text="Delivery in $time",
                    fontSize = 14.sp,
                   color=Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text="Want this later? Schedule it",
                    fontSize = 14.sp,
                    color=Color.DarkGray,
                   textDecoration = TextDecoration.Underline
                )

            }

        }
        HorizontalDivider(modifier=Modifier.padding(horizontal=8.dp, vertical =4.dp))

        //Delivery Address
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Row{
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint=Color.DarkGray,
                    modifier=Modifier.size(18.dp)
                )

                Column(
                    modifier= Modifier
                        .weight(1f)
                        .padding(start=6.dp)
                ){
                    Text(
                        text="Delivery At Home",
                        fontSize=14.sp,
                        color=Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier=Modifier.height(6.dp))
                    Text(
                        text="New Indora Republican Nagar Nagpur....",
                        fontSize=14.sp,
                        color=Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier=Modifier.height(8.dp))
                    Text(
                        text="Add Instruction for Delivery Partner",
                        fontSize=14.sp,
                        color=Color.DarkGray,
                        textDecoration = TextDecoration.Underline
                    )

                }
                Icon(
                    painter=painterResource(R.drawable.arrowright),
                    contentDescription = "Edit",
                    tint=Color.Gray,
                    modifier=Modifier.size(18.dp)
                )
            }

        }
        HorizontalDivider(modifier=Modifier.padding(horizontal=8.dp, vertical =4.dp))

        //contact info

        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                   imageVector = Icons.Outlined.Phone,
                    contentDescription = "Phone",
                    tint=Color.DarkGray,
                    modifier=Modifier.size(18.dp)
                )
                Text(
                    text="Sejal kamble",
                    fontSize=14.sp,
                    color=Color.DarkGray,
                    modifier=Modifier.padding(start=8.dp)
                )
                Text(
                    text="91-123456789",
                    fontSize=14.sp,
                    color=Color.DarkGray,
                    textDecoration = TextDecoration.Underline
                )

            }

            Icon(
               painter=painterResource(R.drawable.arrowright),
                contentDescription = "Edit",
                tint=Color.Gray,
                modifier=Modifier.size(18.dp)
            )

        }
        HorizontalDivider(modifier=Modifier.padding(horizontal=8.dp, vertical =4.dp))

        //bill info
        Row(
            modifier= Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{

                Row{
                    Icon(
                        painter=painterResource(R.drawable.notes),
                        contentDescription = "Bill",
                        tint=Color.DarkGray,
                        modifier=Modifier.size(18.dp)
                    )
                    Text(
                        text="Total Bill $newPrice",
                        fontSize=14.sp,
                        color=Color.DarkGray,
                        modifier=Modifier.padding(8.dp)

                    )
                }
                Text(
                    text="Incl. taxes and charges",
                    fontSize=12.sp,
                    color=Color.Gray,
                    modifier=Modifier.padding(start=28.dp)
                )

            }
            Icon(
                painter=painterResource(R.drawable.arrowright),
                contentDescription = "view",
                tint=Color.Gray,
                modifier=Modifier.size(18.dp)
            )
        }


    }

}

@Composable
fun CouponCard() {
    Card(
        modifier= Modifier.fillMaxWidth()
            .padding(horizontal=8.dp),
        colors= CardDefaults.cardColors(containerColor = Color.White),
        shape=RoundedCornerShape(24.dp)

    ){
        Row(
            modifier=Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter=painterResource(R.drawable.coupons),
                    contentDescription = "Coupons",
                    tint=Color.DarkGray,
                    modifier=Modifier.size(18.dp)
                )
                Text(
                    text="View all coupons",
                    fontSize=16.sp,
                    color=Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier=Modifier.padding(start=8.dp)
                )

            }


            Icon(
                painter=painterResource(R.drawable.arrowright),
                contentDescription = "View",
                tint=Color.Gray,
                modifier=Modifier.size(18.dp)
            )

        }

    }
}

