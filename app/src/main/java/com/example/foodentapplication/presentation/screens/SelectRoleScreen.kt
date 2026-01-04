package com.example.foodentapplication.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.foodentapplication.presentation.components.SelectRoleCard
import com.example.foodentapplication.presentation.navigation.Route
import com.example.foodentapplication.presentation.navigation.route

@Composable
fun SelectRoleScreen(navController: NavController) {

    var selected by remember { mutableStateOf("") }
    var gradientColors = listOf(
           Color(0xFFD7C311),
           Color(0xFFEC7A71)
       )

    val selectedGradient: List<Color> = when (selected) {
        "Student" -> listOf(
            Color(0xFFCC3608),
            Color(0xFFF66A3C)
        )
        "Teacher" -> listOf(
            Color(0xFF4CAF50),
            Color(0xFF87DC25)
        )
        "Admin" -> listOf(
            Color(0xFF3F51B5),
            Color(0xFF2196F3)
        )
        else -> listOf(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurfaceVariant
        )
    }


    val iconBrush = Brush.linearGradient(gradientColors)
    val isEnable = selected.isNotEmpty()
    Column(modifier=Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background),


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){        Column(modifier=Modifier.fillMaxWidth(),

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

            Spacer(modifier=Modifier.height(10.dp))

            Box (
                modifier= Modifier.fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(start = 20.dp, end = 20.dp) // 👈


                    .background(

                            Brush.horizontalGradient(selectedGradient),
                        shape = RoundedCornerShape(16.dp)

                    )
                    .clickable(enabled = isEnable){
                        when(selected){
                            "Student" -> {
                                navController.navigate(Route.UserLoginScreen.route())

                            }
                            "Teacher" -> {
                                navController.navigate(Route.FacultyLogin.route())
                            }
                            "Admin" -> {
                                navController.navigate(Route.AdminLoginScreen.route())
                            }

                        }

                    },


                contentAlignment = Alignment.Center


            ){
                Row(verticalAlignment = Alignment.CenterVertically){

                    Text("Continue",
                        color=Color.White,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier=Modifier.width(12.dp))
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Arrow",
                        tint=Color.White
                    )

                }


            }




        }

    }


}


