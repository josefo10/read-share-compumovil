package com.example.myapplication.navigation

sealed class AppScreens(val route: String) {
    object Charge : AppScreens("Charge")
    object Welcome : AppScreens("Welcome")
    object Login : AppScreens("Login")
    object Sigin : AppScreens("Sigin")

}