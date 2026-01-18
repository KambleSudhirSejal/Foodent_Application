package com.example.foodentapplication.presentation.screens

import android.os.Build

import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.School
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.foodentapplication.common.AppLogger
import com.example.foodentapplication.data.models.UserData
import com.example.foodentapplication.presentation.navigation.Route
import com.example.foodentapplication.presentation.navigation.route
import com.example.foodentapplication.presentation.viewModel.ZomViewModel

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserLoginScreen(navController: NavHostController,
                    viewModel: ZomViewModel= hiltViewModel()
) {

    LaunchedEffect(Unit) {
        AppLogger.ui("UserSignUpScreen","Sign in screen open")
    }

    val loginState by viewModel.loginScreenState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember{mutableStateOf(false)}

    val buttonGradient = listOf(
        Color(0xFFCC3608),
        Color(0xFFF66A3C)
    )

    if(loginState.userData !=null){
        LaunchedEffect(Unit) {
            navController.navigate(Route.UserMainContainer.route()){
                popUpTo(Route.UserLoginScreen.route()){
                    inclusive = true
                }

            }

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Student Portal",
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


            )

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
                        imageVector = Icons.Default.School,
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

                if(loginState.errorMessage !=null){
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text=loginState.errorMessage!!,
                        color=Color.Red,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }



                Spacer(Modifier.height(30.dp))

                // 🔘 Login Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Brush.horizontalGradient(buttonGradient))
                        .clickable(enabled = !loginState.isLoading) {

                            AppLogger.ui("UserLoginScreen","Login Screen clicked ->")

                            viewModel.loginUser(
                                UserData(
                                    name="",
                                    email = email,
                                    password = password,
                                )
                            )

//                            if(loginState.userData !=null){
//                                navController.navigate(Route.UserMainContainer.route()){
//                                    popUpTo(Route.UserLoginScreen.route()){
//                                        inclusive = true
//                                    }
//                                }
//                            }
//                            navController.navigate(Route.UserMainContainer.route()){
//                                //Remove Login from backStack
//
//                                popUpTo(Route.UserLoginScreen.route()){
//                                    inclusive=true
//                                }
//                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if(loginState.isLoading)
                            "Signing..."
                        else
                            "Login",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier=Modifier.height(20.dp))
                Row(
                    modifier=Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ){

                    Text(
                        text = "don't have an account? ",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = " Sign up here",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier=Modifier.clickable{
                            navController.navigate(Route.UserSignUpScreen.route())
                        }

                    )
                }





            }


        }
    }
}

