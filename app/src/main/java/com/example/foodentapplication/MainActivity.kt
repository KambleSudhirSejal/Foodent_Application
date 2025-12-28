package com.example.foodentapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.presentation.navigation.App
import com.example.foodentapplication.ui.theme.FoodentApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {

            //track scroll direction
            var isVisible by remember { mutableStateOf(true) }
            var lastScrollOffset by remember { mutableStateOf(0) }
            var lastIndex by remember { mutableStateOf(0) }
            val listState = rememberLazyListState()

            //listen  to scroll events
            LaunchedEffect(listState){
                snapshotFlow {
                    listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset

                }
                    .distinctUntilChanged()
                    .collect { (index,scrollOffset)->
                        if(index>lastIndex || (index == lastIndex && scrollOffset>lastScrollOffset +50)){
                            //Scrolling down -> Hide BottomNavbar
                            isVisible =false
                        }else if(index < lastIndex || (scrollOffset < lastScrollOffset - 50)){
                            //Scrolling up -> Show BottomNavBar
                            isVisible = true
                    }

                        lastIndex = index
                        lastScrollOffset = scrollOffset
                    }
            }


            FoodentApplicationTheme {
                App(

                    isVisible = isVisible,
                    listState= listState
                )


            }
        }
    }
}


