package com.example.foodentapplication.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodentapplication.R
import com.example.foodentapplication.data.models.CartItem
import com.example.foodentapplication.presentation.utils.OrderPlaceDialog
import com.example.foodentapplication.presentation.viewModel.CartViewModel

@Preview
@Composable
fun PreviewFinalCheckout(){
    val navController= rememberNavController()
    val listState= rememberLazyListState()
//    FinalCheckoutScreen(navController,listState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalCheckoutScreen(navController: NavController, cartViewModel: CartViewModel) {
    var showOrderDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("My Cart") }

    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = name,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "New Indora Republican Nagar , Nagpur .....",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Icon(
                                painter = painterResource(R.drawable.down_arrow),
                                modifier = Modifier.padding(horizontal = 3.dp)
                                    .size(14.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentDescription = "down arrow"
                            )
                        }

                    }

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painterResource(R.drawable.arrowback),
                            contentDescription = "Back Navigation",
                            modifier = Modifier.size(22.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.share),
                            contentDescription = "Back Navigation",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)

            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Button(
                    onClick = {
                        showOrderDialog = true

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Place Order",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_right_24),
                            contentDescription = "Next",
                            modifier = Modifier.align(Alignment.CenterEnd).size(35.dp)
                        )
                    }


                }

            }
            if (showOrderDialog) {
                OrderPlaceDialog(
                    onDismiss = {
                        showOrderDialog = false
                    },
                    onConfirm = {
                        showOrderDialog = false
                        //Add additional configiuration large logc if needed
                    }
                )
            }
        }


    ) { innerPadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            items(
                items=cartItems,
                key={it.foodItem.id}
                ){item->
                CartItemCard(item,
                    onIncreaseQuantity = {
                        cartViewModel.increaseQuantity(item.foodItem.id)
                    },
                    onDecreaseQuantity = {
                        cartViewModel.decreaseQuantity(item.foodItem.id)
                    },
)
            }

            item {
//                CartItemCard()
                Spacer(modifier = Modifier.height(12.dp))
                OrderSummaryCard(cartItems = cartItems)
                Spacer(modifier = Modifier.height(80.dp))
//                PlaceOrderButton()
            }
        }

    }

}












@Composable
fun CartItemCard(item: CartItem,
                 onIncreaseQuantity: () -> Unit,
                 onDecreaseQuantity: () -> Unit) {

    val totalPrice = item.foodItem.price * item.quantity
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ){
                Column {
                    Text(
                        text = item.foodItem.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "₹ ${item.foodItem.price}",
                        color = Color(0xFFFF7A00),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                AsyncImage(
                    model = item.foodItem.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.width(70.dp)
                        .height(100.dp),
                    contentScale = ContentScale.Crop,


                    )


            }



            Spacer(modifier = Modifier.height(12.dp))



                Column(horizontalAlignment = Alignment.End) {
                    Text("TOTAL", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        "₹ ${totalPrice.toString()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            QuantitySelector(
                quantity = item.quantity,
                onIncreaseQuantity = onIncreaseQuantity,
                onDecreaseQuantity = onDecreaseQuantity
            )
        }
    }



@Composable
fun QuantitySelector(
    quantity: Int,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(14.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text("Quantity", color = MaterialTheme.colorScheme.onSurface)

        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(
                onClick = { if (quantity > 1) onDecreaseQuantity() }
            ) {
                Icon(Icons.Default.Remove,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = quantity.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,

            )

            IconButton(
                onClick = { onIncreaseQuantity()},


            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun OrderSummaryCard(cartItems: List<CartItem>) {

    val subTotal = cartItems.sumOf {
        it.foodItem.price*it.quantity
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Order Summary",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            SummaryRow("Subtotal (${cartItems.size}) items", "₹$subTotal")

            SummaryRow("Service Fee", "Free", Color(0xFF2ECC71))

            Divider(modifier = Modifier.padding(vertical = 12.dp))

            SummaryRow(
                "Total",
                "₹ $subTotal",
                Color(0xFFFF7A00),
                isBold = true
            )
        }
    }
}

@Composable
fun SummaryRow(
    title: String,
    value: String,
    valueColor: Color = Color.Black,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(
            value,
            color = valueColor,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}







