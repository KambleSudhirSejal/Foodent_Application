package com.example.foodentapplication.presentation.navigation

import kotlinx.serialization.Serializable
import java.io.Serial

sealed class SubNavigation {

    @Serializable
    object LoginSignUpScreen: SubNavigation()

    @Serializable
    object MainHomeScreen: SubNavigation()

    @Serializable
    object UserMainHomeScreen: SubNavigation()

    @Serializable
    object AdminMainHomeScreen: SubNavigation()



}

sealed class Route{

    @Serializable
    object SelectRoleScreen : Route()

    @Serializable
    object UserLoginScreen : Route()

    @Serializable
    object UserSignUpScreen : Route()

    @Serializable
    object UserCartScreen : Route()

    @Serializable
    object UserProfileScreen : Route()


    @Serializable
    object UserHomeScreen : Route()

    @Serializable
    object AdminAddItemScreen : Route()

    @Serializable
    object AdminMenuScreen : Route()

    @Serializable
    object AdminLoginScreen : Route()



    @Serializable
    object AdminProfileScreen : Route()

    @Serializable
    object AdminOrderScreen : Route()






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
    object FacultyLogin:Route()

    @Serializable
    object FacultySignUp:Route()

    @Serializable
    object SearchBarScreen:Route()


}