package com.example.foodentapplication.presentation.utils

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.foodentapplication.R


@Preview
@Composable
fun OrderPlacedPreview(){
    val onDismiss:()->Unit
    val onConfirm:()->Unit
    OrderPlaceDialog(onDismiss={},onConfirm={})
}


@Composable
fun OrderPlaceDialog(
    onDismiss:()->Unit,
    onConfirm:()->Unit
) {
    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = colorResource(R.color.purple_500),
        targetValue = colorResource(    R.color.teal_200),
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    var tokenNumber by remember {mutableStateOf("146543")}

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ){
        Card(
            modifier=Modifier.height(550.dp).width(350.dp)
               ,
                    colors= CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            shape= RoundedCornerShape(14.dp)
        ){

            Column(

                modifier = Modifier.fillMaxSize().padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Image(
                    painter = painterResource(R.drawable.check_right),
                        contentDescription = null,

                   modifier= Modifier
                       .size(42.dp)
                )

                Spacer(modifier=Modifier.height(10.dp))
                Text(
                    text="Order Confirmed !",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color=color
                )
                Spacer(modifier=Modifier.height(5.dp))

                Text(
                    text="Your Order has been placed Successfully",
                    fontSize = 14.sp,

                    textAlign = TextAlign.Center,
                    color=MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    style = TextStyle(lineHeight = 16.sp)

                )

                Spacer(modifier=Modifier.height(20.dp))

                Card(
                    modifier=Modifier.fillMaxWidth()
                        .height(150.dp)

                ){

                    Column(
                        modifier=Modifier.fillMaxSize().padding(10.dp),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text("TOKEN NUMBER")
                        Spacer(modifier=Modifier.height(15.dp))

                        Row(
                            modifier=Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center


                        ){
                            Image(
                                painter=painterResource(R.drawable.tokenization),
                                contentDescription = null,
                                modifier=Modifier.size(25.dp)

                            )
                            Spacer(modifier=Modifier.width(5.dp))
                            Text(
                                text=tokenNumber,
                               style= TextStyle(
                                   fontSize=30.sp,
                                   fontWeight = FontWeight.ExtraBold,
                                   color= MaterialTheme.colorScheme.onSecondaryContainer,

                               ),
                                modifier=Modifier.padding(end=10.dp)

                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            text="Show this token when Collecting your order",
                            textAlign = TextAlign.Center,
                            fontSize=12.sp,
                            color=MaterialTheme.colorScheme.onSurfaceVariant
                        )

                    }


                }

                Spacer(modifier=Modifier.height(10.dp))

                Button(
                    onClick = {
                        onDismiss
                    },
                    colors= ButtonDefaults
                        .buttonColors(containerColor =Color.Unspecified ),
                    modifier = Modifier.padding(10.dp).fillMaxWidth(0.60f),
                    shape = RoundedCornerShape(10.dp)


                ){
                    Text(
                        text="Continue Shopping",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                }


            }

        }


    }




}