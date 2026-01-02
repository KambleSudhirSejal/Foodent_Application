package com.example.foodentapplication.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectRoleCard(
    icon: ImageVector,
    title: String,
    subTitle: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    gradientColors:List<Color>

    ) {

    val iconBrush = Brush.linearGradient(gradientColors)
    Card(
        modifier=Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable(onClick=onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        )

    ){

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier=Modifier.size(38.dp).background(
                    brush = iconBrush, shape = RoundedCornerShape(12.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,

                )
            }



            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subTitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector =
                    if (isSelected)
                        Icons.Default.RadioButtonChecked
                    else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if(isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier= Modifier
                    .size(24.dp)
                    .drawWithCache{
                        onDrawWithContent {
                            drawContent()
                            if(isSelected){
                                drawRect(
                                    brush=iconBrush,
                                    blendMode = BlendMode.SrcAtop
                                )
                            }
                        }
                    }


            )


        }

    }


}