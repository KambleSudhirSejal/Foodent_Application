package com.example.foodentapplication.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodentapplication.presentation.components.InfoCard
import com.example.foodentapplication.presentation.components.LogoutButton
import com.example.foodentapplication.presentation.components.ProfileHeader
import com.example.foodentapplication.presentation.components.SectionTitle
import com.example.foodentapplication.presentation.components.SettingsItems
import com.example.foodentapplication.ui.theme.TextGray


@Composable
fun FacultyProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()


            .verticalScroll(rememberScrollState())
    ) {

        ProfileHeader("User", Color(0xFF4CAF50))

        SectionTitle("ACCOUNT INFORMATION")

        InfoCard(
            icon = Icons.Default.Email,
            title = "Email",
            value = "kamblesejal277@gmail.com",
            showArrow = true,
            Color(0xFF4CAF50)
        )

        InfoCard(
            icon = Icons.Default.Phone,
            title = "Phone",
            value = "Add phone number",
            showArrow = true,
            Color(0xFF4CAF50)
        )

        InfoCard(
            icon = Icons.Default.School,
            title = "Account Type",
            value = "Student",
            showArrow = true,
            Color(0xFF4CAF50)
        )

        SectionTitle("SETTINGS")

        SettingsItems(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            subtitle = "Manage your notifications",
            Color(0xFF4CAF50)

        )

        SettingsItems(
            icon = Icons.Default.Payment,
            title = "Payment Methods",
            subtitle = "Add or remove payment options",
            Color(0xFF4CAF50)

        )

        SettingsItems(
            icon = Icons.Default.Lock,
            title = "Privacy & Security",
            subtitle = "Manage your account security",
            Color(0xFF4CAF50)

        )

        SettingsItems(
            icon = Icons.Default.Help,
            title = "Help & Support",
            subtitle = "Get help or contact us",
            Color(0xFF4CAF50)

        )

        Spacer(modifier = Modifier.height(24.dp))

        LogoutButton()

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Version 1.0.0",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 12.sp,
            color = TextGray
        )
    }
}

