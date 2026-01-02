package com.example.foodentapplication.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.foodentapplication.ui.theme.AdminDark1
import com.example.foodentapplication.ui.theme.AdminDark2
import com.example.foodentapplication.ui.theme.AdminLight1
import com.example.foodentapplication.ui.theme.AdminLight2
import com.example.foodentapplication.ui.theme.StudentDark1
import com.example.foodentapplication.ui.theme.StudentDark2
import com.example.foodentapplication.ui.theme.StudentLight1
import com.example.foodentapplication.ui.theme.StudentLight2
import com.example.foodentapplication.ui.theme.TeacherDark1
import com.example.foodentapplication.ui.theme.TeacherDark2
import com.example.foodentapplication.ui.theme.TeacherLight1
import com.example.foodentapplication.ui.theme.TeacherLight2

enum class UserRole(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val lightGradient: List<Color>,
    val darkGradient: List<Color>) {

    STUDENT(
        "Student",
        "Access your courses & assignments",
        Icons.Default.School,
        listOf(StudentLight1, StudentLight2),
        listOf(StudentDark1, StudentDark2)
    ),
    TEACHER(
        "Teacher",
        "Manage classes & track progress",
        Icons.Default.MenuBook,
        listOf(TeacherLight1, TeacherLight2),
        listOf(TeacherDark1, TeacherDark2)
    ),
    ADMIN(
        "Admin",
        "Full system administration",
        Icons.Default.Security,
        listOf(AdminLight1, AdminLight2),
        listOf(AdminDark1, AdminDark2)
    )


}