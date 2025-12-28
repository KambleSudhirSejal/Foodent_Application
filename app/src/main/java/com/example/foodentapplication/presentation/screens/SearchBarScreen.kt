package com.example.foodentapplication.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodentapplication.R
import com.example.foodentapplication.data.models.FoodCategory
import com.example.foodentapplication.data.models.TabItem
import com.example.foodentapplication.presentation.utils.SearchDiningTabScreen

/* ---------------------------------------------------------
   PREVIEW
--------------------------------------------------------- */
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    val navController = rememberNavController()
    SearchBarScreen(navController = navController)
}

/* ---------------------------------------------------------
   SEARCH BAR SCREEN
--------------------------------------------------------- */
@Composable
fun SearchBarScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrowback),
                        contentDescription = "Back",
                        tint = colorResource(R.color.purple_200),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { navController.popBackStack() }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.weight(1f),
                        textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            Box {
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        text = "Search \"Restaurant\"",
                                        color = Color.Gray,
                                        fontSize = 15.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    VerticalDivider(
                        modifier = Modifier
                            .height(24.dp)
                            .padding(horizontal = 8.dp),
                        thickness = 0.5.dp,
                        color = Color.LightGray
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.mic),
                        contentDescription = "Voice",
                        tint = colorResource(R.color.purple_700),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        SearchTabs()
    }
}

/* ---------------------------------------------------------
   TABS
--------------------------------------------------------- */
@Composable
fun SearchTabs() {
    val tabs = listOf(
        TabItem("Delivery"),
        TabItem("Dining")
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    color = colorResource(R.color.purple_500)
                )
            }
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedIndex) {
            0 -> FoodCategoryList()
            1 -> SearchDiningTabScreen()
        }
    }
}

/* ---------------------------------------------------------
   FOOD CATEGORY GRID
--------------------------------------------------------- */
@Composable
fun FoodCategoryList() {
    Text(
        text = "WHAT'S ON YOUR MIND?",
        modifier = Modifier.padding(start = 16.dp, top = 12.dp),
        style = TextStyle(
            fontSize = 14.sp,
            color = Color.Gray,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.SansSerif
        )
    )

    val foodCategories = listOf(
        FoodCategory("All", R.drawable.allfood),
        FoodCategory("Burger", R.drawable.burger),
        FoodCategory("Pizza", R.drawable.pizza_image),
        FoodCategory("Sweets", R.drawable.sweets),
        FoodCategory("Biryani", R.drawable.vegbiryani),
        FoodCategory("Desserts", R.drawable.ice_cream),
        FoodCategory("Rolls", R.drawable.rolls),
        FoodCategory("Pasta", R.drawable.pasta),
        FoodCategory("Chinese", R.drawable.chinese)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(foodCategories.size) { index ->
            FoodCategoryItem(
                category = foodCategories[index],
                isSelected = index == selectedIndex,
                onClick = { selectedIndex = index }
            )
        }
    }
}

/* ---------------------------------------------------------
   CATEGORY ITEM
--------------------------------------------------------- */
@Composable
fun FoodCategoryItem(
    category: FoodCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
