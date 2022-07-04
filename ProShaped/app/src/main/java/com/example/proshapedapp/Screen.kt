package com.example.proshapedapp

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object MacrosScreen: Screen("macros?id={id}")
    object CaloriesScreen: Screen("calories")
    object WorkoutScreen: Screen("workout")
    object GenderPicker: Screen ("genderPicker")

    fun passId(id: Int): String{
        return "macros?id=$id"
    }
}
