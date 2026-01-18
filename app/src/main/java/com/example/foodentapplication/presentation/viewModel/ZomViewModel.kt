package com.example.foodentapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodentapplication.common.AppLogger
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

        AppLogger.viewModel(
            "ZoomViewmodel","createUser called with $userData"
        )

        if(userData.name.isBlank()){
            AppLogger.viewModel("ZoomViewModel","Validation failed: name empty")
            return
        }
        viewModelScope.launch {

            AppLogger.viewModel("ZoomViewModel","Starting the signup process")

            _signUpScreenState.value = _signUpScreenState.value.copy(isLoading = true)

            when (val result = createUserUseCase.createUser(userData)) {
                is ResultState.Success -> {
                    AppLogger.viewModel(
                        "ZoomViewModel","signup success"
                    )
                    _signUpScreenState.value = _signUpScreenState.value.copy(
                        isLoading = false,
                        userData = result.data
                    )
                }

                is ResultState.Error -> {
                    AppLogger.viewModel(
                        "ZoomViewModel","login failed"
                    )
                    _signUpScreenState.value = _signUpScreenState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                else -> {}

            }

        }

    }

    fun loginUser(userData: UserData){

        AppLogger.viewModel(
            "ZoomViewModel","loginUser called  for $userData"
        )
        viewModelScope.launch {

            AppLogger.viewModel(
                "ZoomViewModel","starting the login process"
            )

            _loginScreenState.value = _loginScreenState.value.copy(isLoading = true)

            when (val result = loginUserUseCase.loginUser(userData)) {
                is ResultState.Success -> {
                    AppLogger.viewModel(
                        "ZoomViewModel","login success"
                    )
                    _loginScreenState.value = _loginScreenState.value.copy(
                        isLoading = false,
                        userData = result.data
                    )
                }

                is ResultState.Error -> {
                    AppLogger.viewModel(
                        "ZoomViewModel","login failed"
                    )
                    _loginScreenState.value = _loginScreenState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }

                else -> {}

            }

        }

        }

    }

data class SignUpScreenState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val userData: UserData?=null
)

data class LoginScreenState(
    val isLoading:Boolean=false,
    val errorMessage:String?=null,
    val userData:String?=null
)


