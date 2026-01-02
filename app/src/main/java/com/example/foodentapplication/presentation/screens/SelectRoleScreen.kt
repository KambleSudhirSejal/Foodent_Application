package com.example.foodentapplication.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodentapplication.R
import com.example.foodentapplication.presentation.components.SelectRoleCard
import kotlinx.coroutines.selects.select


@Composable
fun SelectRoleScreen(

){

    var selected by remember { mutableStateOf("Teacher") }

     val  gradientColors = listOf(
           Color(0xFFD7C311),
           Color(0xFFEC7A71)
       )


    val iconBrush = Brush.linearGradient(gradientColors)
    Column(modifier=Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){

        Column(modifier=Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Box(
                modifier=Modifier.size(68.dp).background(
                    brush = iconBrush, shape = RoundedCornerShape(22.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Default.FoodBank,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)

                    )
            }
            Spacer(modifier=Modifier.height(25.dp))
            Text(
                text="Welcome To FOODENT",
                modifier=Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color= Color.Black
                ),
                color = MaterialTheme.colorScheme.onSurface

            )

            Spacer(modifier=Modifier.height(8.dp))


            Text(
                text="Select Your Role to Continue",
                modifier=Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style= TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color= Color.Gray
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant

            )


        }




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ){




            Spacer(modifier=Modifier.height(10.dp))

            SelectRoleCard(
                icon = Icons.Default.School,
                title = "Student",
                subTitle = "Access courses & assignments",
                isSelected = selected == "Student",
                onClick = { selected="Student" },
                gradientColors = listOf(
                    Color(0xFFCC3608),
                    Color(0xFFF66A3C)
                )
            )

            SelectRoleCard(
                icon = Icons.Default.MenuBook,
                title = "Teacher",
                subTitle = "Manage classes",
                onClick = { selected="Teacher" },
                isSelected = selected == "Teacher",
                gradientColors = listOf(
                    Color(0xFF4CAF50),
                    Color(0xFF87DC25)
                )
            )

            SelectRoleCard(
                icon = Icons.Default.Security,
                title = "Admin",
                subTitle = "System administration",
                onClick = {selected ="Admin"},
                isSelected = selected == "Admin",
                gradientColors = listOf(
                    Color(0xFF3F51B5),
                    Color(0xFF2196F3)
                )
            )



        }

    }


}


