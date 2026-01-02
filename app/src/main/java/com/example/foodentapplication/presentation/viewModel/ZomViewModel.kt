package com.example.foodentapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodentapplication.common.ResultState
import com.example.foodentapplication.data.models.UserData
import com.example.foodentapplication.domain.useCases.CreateUserUseCase
import com.example.foodentapplication.domain.useCases.LoginUserUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ZomViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val loginUserUseCase: LoginUserUseCase

) : ViewModel(){
    private val _signUpScreenState= MutableStateFlow(SignUpScreenState())
    val signUpScreenState =_signUpScreenState.asStateFlow()

    private val _loginScreenState=MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun  createUser(userData : UserData){
        viewModelScope.launch {
            createUserUseCase.createUser(userData).collect {
                when(it){
                    is ResultState.Error->{
                        _signUpScreenState.value=_signUpScreenState.value.copy(
                            isLoading=false,
                            errorMessage=it.message
                        )
                    }
                    ResultState.Loading -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success->{

                        _signUpScreenState.value=_signUpScreenState.value.copy(
                            isLoading=false,
                            errorMessage=it.data
                        )
                    }
                }

            }

        }



    }

    fun loginUser(userData: UserData){
        viewModelScope.launch {
            loginUserUseCase.loginUser(userData).collect {
                when(it){
                    is ResultState.Error->{
                        _loginScreenState.value=_loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading->{

                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success<*> -> {
                        _loginScreenState.value=_loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.data as String?
                        )
                    }

                }

            }
        }
    }


}

data class SignUpScreenState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val userData:String?=null
)

data class LoginScreenState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val userData:String?=null
)