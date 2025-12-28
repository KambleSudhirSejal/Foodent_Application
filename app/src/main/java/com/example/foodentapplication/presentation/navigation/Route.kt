package com.example.foodentapplication.presentation.navigation

import kotlinx.serialization.Serializable
import java.io.Serial

sealed class SubNavigation {

    @Serializable
    object LoginSignUpScreen: SubNavigation()

    @Serializable
    object MainHomeScreen: SubNavigation()



}

sealed class Route{

    @Serializable
    object LoginScreen : Route()

    @Serializable
    object SignUpScreen:Route()

    @Serializable
    object DeliveryScreen:Route()

    @Serializable
    object QuickScreen:Route()


    @Serializable
    object DiningScreen:Route()

    @Serializable
    object ProfileScreen:Route()

    @Serializable
    object ParticularCardScreen:Route()

    @Serializable
    object FinalCheckOutScreen:Route()

    @Serializable
    object SearchBarScreen:Route()
}