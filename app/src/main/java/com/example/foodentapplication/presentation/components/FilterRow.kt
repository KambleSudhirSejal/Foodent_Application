package com.example.foodentapplication.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodentapplication.R

@Preview(showBackground = true)
@Composable
fun PreviewFilterRow(){
    var filters = listOf<String>("Filter",
        "Flash Scale",
        "Under 30 mins",
        "Rating",
        "Schedule")

    FilterRow(filters)
}



@Composable
fun FilterRow(filters: List<String>) {

    var selectedFilter by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(filters) { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label={
                        Text(filter,fontSize=12.sp)

                    },
                    enabled=true,
                    border = BorderStroke(0.4.dp,Color.Gray),

                    leadingIcon = {
                        if (filter == "Filter") {
                            Icon(
                                painter = painterResource(R.drawable.dining),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (filter == "Flash Scale") {
                            Icon(
                                painter = painterResource(R.drawable.snack_meal),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (filter == "Under 30 mins") {
                            Icon(
                                painter = painterResource(R.drawable.timer),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (filter == "Rating") {
                            Icon(
                                painter = painterResource(R.drawable.rating),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (filter == "Schedule") {
                            Icon(
                                painter = painterResource(R.drawable.notes),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        selectedContainerColor = Color.LightGray,
                        labelColor = Color.DarkGray
                    ),
                    trailingIcon = {
                        if (filter == "Filter") {
                            Icon(
                                painter = painterResource(R.drawable.arrowdown),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (selectedFilter=="Flash Scale" && filter == "Flash Scale" ) {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (selectedFilter=="Under 30 mins" && filter == "Under 30 mins" ) {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (selectedFilter=="Rating" && filter == "Rating" ) {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        if (selectedFilter=="Schedule" && filter == "Schedule" ) {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }




                    }
                )
            }
        }
    }
}




