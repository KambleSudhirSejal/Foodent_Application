package com.example.foodentapplication.presentation.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodentapplication.R
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.FoodCategoryData
import com.example.foodentapplication.data.models.FoodItem
import com.example.foodentapplication.domain.domainModule.CloudinaryUploader
import com.example.foodentapplication.presentation.components.CardImagesRow
import com.example.foodentapplication.presentation.components.PageCount
import com.example.foodentapplication.presentation.components.PriceCard
import com.example.foodentapplication.presentation.viewModel.AddFetchItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMenuScreen(navController: NavController,
                    viewModel: AddFetchItemViewModel= hiltViewModel()) {

   var showAddNewItem by remember {mutableStateOf(false)}

    val addFoodState by viewModel.addFoodState.collectAsState()
    val foodListState  by viewModel.foodListState.collectAsState()
   Scaffold(
       topBar = {
           TopAppBar(
               title={
                   Column{
                       Row{
                           Icon(
                               imageVector = Icons.Default.AutoGraph,
                               contentDescription = null,
                               tint= Color.Blue
                           )
                           Spacer(modifier=Modifier.width(5.dp))
                           Text(
                               text="Dashboard",

                           )
                       }
                       Text(
                           text="Overview And menu Management",
                           fontSize = 15.sp
                       )
                   }
                     },
               colors= TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background)

           )
       }
   )

   { innerPadding->

       LazyColumn(
           modifier=Modifier.fillMaxSize()
               .padding(innerPadding)
       ){
           item{

               Spacer(modifier=Modifier.height(10.dp))
               DashBoardCard()
               Spacer(modifier=Modifier.height(5.dp))

               Button(
                   onClick = {

                       showAddNewItem = true
                   },
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 16.dp, vertical = 20.dp),
                   colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5079E0)),
                   shape = RoundedCornerShape(15.dp),
                   elevation = ButtonDefaults.buttonElevation(pressedElevation = 10.dp)
               ) {
                   Box(
                       modifier = Modifier.fillMaxWidth().padding(5.dp)
                   ) {

                       Icon(
                            imageVector = Icons.Default.Add,
                           contentDescription = "Next",
                           modifier = Modifier.align(Alignment.CenterStart).size(25.dp),
                           tint=Color.White

                       )
                       Text(
                           text = "Add New Food Item",
                           fontSize = 18.sp,
                           fontWeight = FontWeight.Bold,
                           modifier = Modifier.align(Alignment.Center),
                           color=Color.White
                       )

                   }

               }
               Spacer(modifier=Modifier.height(10.dp))

               if(showAddNewItem){
                   AddNewItem(
                           addFoodState,
                           onSubmit={ foodItem ->
                               viewModel.addFood(foodItem )
                           },
                           onClose ={
                               viewModel.resetAddFoodState()
                               viewModel.refreshFood()
                               showAddNewItem = false


                           }
                       )
               }

               Spacer(modifier=Modifier.height(10.dp))

               Spacer(modifier=Modifier.height(10.dp))

               ShowingAllItems(navController,foodListState,viewModel)

           }
       }
   }

}


@Composable
fun ShowingAllItems(
    navController: NavController,
    foodListState: ResultState<List<FoodItem>>,
    viewModel: AddFetchItemViewModel
){

    LaunchedEffect(Unit) {
        viewModel.fetchFoods("all")
    }

        when(foodListState){
          is ResultState.Loading->{


                  Box(
                      modifier = Modifier.fillMaxWidth()
                          .height(200.dp),
                      contentAlignment = Alignment.Center
                  ){
                      CircularProgressIndicator()
                  }




          }

            is ResultState.Success -> {


                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    foodListState.data.forEach { food ->
                        FoodItemCard(
                            navController = navController,
                            foodItem = food
                        )
                    }
                }



            }

            is ResultState.Error -> {

                    Text(
                        text = "Failed to load food items",
                        modifier=Modifier.padding(16.dp),
                        color = Color.Red
                    )
            }

            else ->  Unit
        }


}



@Composable
fun FoodItemCard(navController: NavController,foodItem: FoodItem){
    Card( onClick = {},
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier= Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
    {

        Box(modifier = Modifier.fillMaxWidth()){



                AsyncImage(
                    model = foodItem.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
//                        .height(200.dp)
                    contentScale = ContentScale.Crop,


                )





            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp,start=12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.Center){

                PriceCard(
                    name=foodItem.name,
                    price="Rs. ${foodItem.price}"
                )



                IconButton(onClick = {}){
                    Icon(painterResource(R.drawable.baseline_bookmark_24),
                        contentDescription = "Book Mark",
                        tint=Color.Black.copy(alpha =0.6f ),
                        modifier = Modifier.size(34.dp)
                    )
                }

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 192.2.dp)
               ){

                FoodItemName(foodItem)
            }
        }
    }
}

@Composable
fun FoodItemName(foodItem1: FoodItem) {


        Card(
            modifier = Modifier.fillMaxWidth().padding(),
            colors= CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(bottomStart = 22.dp, bottomEnd = 22.dp)
        ){
            Column(modifier = Modifier.fillMaxSize()){
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start =16.dp,top=8.dp,end=16.dp),
                    Arrangement.SpaceBetween
                ){

                    Column(modifier=Modifier.weight(1f).padding(bottom = 20.dp)){
                        Text(
                            text = foodItem1.name,
                            color= MaterialTheme.colorScheme.onBackground,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier=Modifier.height(5.dp))
                        Text(
                            text = foodItem1.description,
                            color= MaterialTheme.colorScheme.onSurface,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier=Modifier.height(5.dp))



                        Text(
                            text= "Rs. ${foodItem1.price}",
                            color= MaterialTheme.colorScheme.onBackground,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold

                        )
                    }
                    Spacer(modifier = Modifier.weight(0.3f))

                    Row(
                        modifier=Modifier.padding(top = 25.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically){

                        IconButton(
                                  onClick = {},
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    color = Color(0xFFE3F2FD),
                                    shape = CircleShape
                                )

                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint=Color(0xFF1976D2)
                                )

                        }

                        Spacer(modifier = Modifier.width(15.dp))

                        // 🗑 Delete Button
                        IconButton(
                            onClick = {
                                // TODO: Delete action
                            },
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    color = Color(0xFFFFEBEE),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color(0xFFD32F2F)
                            )
                        }

                    }

                }
            }
        }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardCard() {
    Card(
        modifier= Modifier.fillMaxWidth()
            .padding(16.dp)
            ,
        shape= RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors= CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)


    ){
        Row(
            modifier=Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column(
               modifier=Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally
            ){
                Text(
                    text="50",
                    style= TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF9C27B0)
                    )
                )
                Text(
                    text="Total Orders",
                    style= TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
            Spacer(modifier=Modifier.weight(1f))
            Column(
                modifier=Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally
            ){
                Text(
                    text="50",
                    style= TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFFF52B1C)
                    )
                )
                Text(
                    text="Pendings",
                    style= TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }
            Spacer(modifier=Modifier.weight(1f))
            Column(
                modifier=Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally
            ){
                Text(
                    text="50",
                    style= TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        color = Color(0xFF44CE4A)
                    )
                )
                Text(
                    text="Compeleted",
                    style= TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                )
            }

        }
    }

}


@SuppressLint("ShowToast")
@Composable
fun AddNewItem(

    addFoodState: ResultState<String>,
    onSubmit: (FoodItem) -> Unit,
    onClose: () -> Unit
){

    var imageUri by remember {mutableStateOf<Uri?>(null)}

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        uri ->
        imageUri = uri
    }
    val context = LocalContext.current
    val categories = listOf(
        FoodCategoryData("All", R.drawable.allfood),
        FoodCategoryData("Pizza", R.drawable.pizza_image),
        FoodCategoryData("chinese", R.drawable.chinese),
        FoodCategoryData("Burgers", R.drawable.burger),
        FoodCategoryData("Pulao", R.drawable.veg_biryani),
        FoodCategoryData("Sweets", R.drawable.sweets),
        FoodCategoryData("Pasta", R.drawable.pasta),
        FoodCategoryData("Rolls", R.drawable.rolls),
        FoodCategoryData("Ice Cream", R.drawable.ice_cream),



        )

    var selectedCategory by remember { mutableStateOf("All") }


    var itemName  by remember {mutableStateOf("")}
    var description by remember {mutableStateOf("")}
    var price by remember {mutableStateOf("")}



        Column( modifier=Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment= Alignment.CenterHorizontally){

            Text(
                text="Add New Item",
                style= TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color= MaterialTheme.colorScheme.onBackground

                )
            )

            Text(
                text="Food Item Name ",
                modifier=Modifier.fillMaxWidth().padding(10.dp).align(Alignment.Start),
                style= TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color= MaterialTheme.colorScheme.onSurface

                )
            )

            Spacer(modifier=Modifier.height(5.dp))

            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                placeholder = { Text("Add Food Item Name") },
                leadingIcon = {
                    Icon(Icons.Default.FoodBank, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Text(
                text="Price ",
                modifier=Modifier.fillMaxWidth().padding(10.dp).align(Alignment.Start),
                style= TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color= MaterialTheme.colorScheme.onSurface

                )
            )
            Spacer(modifier=Modifier.height(5.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                placeholder = { Text("Rs. 20") },
                leadingIcon = {
                    Icon(Icons.Default.PriceCheck, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Text(
                text="Description ",
                modifier=Modifier.fillMaxWidth().padding(10.dp).align(Alignment.Start),
                style= TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color= MaterialTheme.colorScheme.onSurface

                )
            )

            Spacer(modifier=Modifier.height(5.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text("Food Description ...") },
                leadingIcon = {
                    Icon(Icons.Default.Description, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Spacer(modifier=Modifier.height(10.dp))

            Column(
                modifier=Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,

                ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Category ",
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface

                        )
                    )
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)

                ) {
                    items(categories) { category ->
                        CategoryItem(
                            category = category,
                            isSelected = selectedCategory == category.name,
                            onClick = {
                                selectedCategory = category.name
                            }
                        )
                    }
                }
            }
            Spacer(modifier=Modifier.height(10.dp))

            Text(
                text = "Food Image",
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical=8.dp),
                fontSize = 12.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(160.dp)
                    .clickable{
                        imagePickerLauncher.launch("image/*")
                    },
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )

            ){

                Box(
                    contentAlignment = Alignment.Center
                ){
                    if(imageUri == null){
                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Tap to select image ")
                        }

                    }else{
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }
                }


            }


            Spacer(modifier=Modifier.height(20.dp))
            Button(

                onClick = {
                    if (
                        itemName.isBlank() ||
                        price.isBlank() ||
                        description.isBlank() ||
                        imageUri == null
                    ) {
                        return@Button
                    }

                    CloudinaryUploader.uploadImage(
                        context = context,
                        imageUri = imageUri!!,
                        onSuccess = { imageUri ->
                            val foodItem = FoodItem(
                                name = itemName,
                                price = price.toInt(),
                                description = description,
                                category = selectedCategory.lowercase(),
                                imageUrl = imageUri
                            )

                            onSubmit(foodItem)
                        },
                        onError = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                },




                modifier = Modifier
                    .fillMaxWidth()
                    ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5079E0)),
                shape = RoundedCornerShape(15.dp),
                elevation = ButtonDefaults.buttonElevation(pressedElevation = 10.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(5.dp)
                ) {


                    Text(
                        text = " Add $selectedCategory",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center),
                        color=Color.White
                    )

                }
            }

            Spacer(modifier=Modifier.height(16.dp))


            when (addFoodState){
                is ResultState.Loading -> {

                }
                is ResultState.Success -> {

                        LaunchedEffect(addFoodState) {
                            if(addFoodState is ResultState.Success){
                                Toast.makeText(context,"Food added successfully", Toast.LENGTH_SHORT).show()
                                onClose()
                            }
//                            Toast.makeText(context,"Food added successfully", Toast.LENGTH_SHORT).show()
//                            onClose()
                        }
                }

                is ResultState.Error -> {
                    LaunchedEffect(Unit) {
                            Toast.makeText(context,"Food addition Failed", Toast.LENGTH_SHORT).show()

                            onClose()
                        }
                }
                else -> {

                }
            }
        }
}



@Composable
fun CategoryItem(
    category: FoodCategoryData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected)
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp)
    ) {

        Image(
            painter = painterResource(id = category.image),
            contentDescription = category.name,
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            color = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface
        )
    }
}
