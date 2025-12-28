package com.example.foodentapplication.domain.useCases

import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.UserData
import com.example.foodentapplication.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(val repo: Repo) {

    fun loginUser(userData: UserData): Flow<ResultState<String>>{
        return repo.loginWithEmailAndPassword(userData )
    }

}