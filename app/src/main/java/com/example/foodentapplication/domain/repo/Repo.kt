package com.example.foodentapplication.domain.repo

import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.UserData
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun loginWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>
    fun registerWithEmailAndPassword(userData: UserData): Flow<ResultState<String>>

}