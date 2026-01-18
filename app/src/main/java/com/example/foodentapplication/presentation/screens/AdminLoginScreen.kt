package com.example.foodentapplication.presentation.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.foodentapplication.common.AdminConfig
import com.example.foodentapplication.common.AppLogger
import com.example.foodentapplication.presentation.navigation.Route
import com.example.foodentapplication.presentation.navigation.route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLoginScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember{mutableStateOf(false)}
    val isButtonEnabled = email.isNotEmpty() && password.isNotBlank()

    val context = LocalContext.current

    AppLogger.ui("AdminLoginScreen","Enter into admin login screen")

    val buttonGradient = listOf(
        Color(0xFF3F51B5),
        Color(0xFF2196F3)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Admin Portal",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                // 🔽 BOTTOM BAR INSIDE SCAFFOLD

            )

        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // 👈 VERY IMPORTANT
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Admin accounts are created by the system administrator",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔽 CENTER CONTENT
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔒 Lock Icon
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(Brush.linearGradient(buttonGradient)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(42.dp)
                    )
                }

                Spacer(Modifier.height(30.dp))

                Text(
                    text="Login Here!",
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
                    text="Enter the Login Credentials with email and password",
                    modifier=Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style= TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color= Color.Gray
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier=Modifier.height(30.dp))

                // 📧 Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email Address") },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                // 🔑 Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Password") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null)
                    },
                    trailingIcon = {
                        IconButton(onClick = {passwordVisible = !passwordVisible}){

                            Icon(
                                imageVector = if(passwordVisible)
                                Icons.Default.VisibilityOff
                                else
                                Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    visualTransformation = if(passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()
                )



                Spacer(Modifier.height(30.dp))

                // 🔘 Login Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if(isButtonEnabled)
                            Brush.horizontalGradient(buttonGradient)
                            else
                                Brush.horizontalGradient(
                                    listOf(Color.Gray, Color.LightGray)
                                )
                        )
                        .clickable(enabled = isButtonEnabled ) {

                            AppLogger.ui("AdminLoginScreen","Button is clicked")

                            if(email == AdminConfig.ADMIN_EMAIL && password == AdminConfig.ADMIN_PASSWORD) {

                                AppLogger.ui("AdminLoginScreen","Login Successful")

                                Toast.makeText(context,"Admin Login successfully",Toast.LENGTH_SHORT).show()
                                navController.navigate(Route.AdminMainContainer.route()) {
                                    //Remove Login from backStack

                                    popUpTo(Route.AdminLoginScreen.route()) {
                                        inclusive = true
                                    }
                                }
                            }else{
                                AppLogger.ui("AdminLoginScreen","Login failed")
                                Toast.makeText(context,"Invalid admin email or password",Toast.LENGTH_SHORT).show()
                                "Invalid admin or password"
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }


        }
    }
}
