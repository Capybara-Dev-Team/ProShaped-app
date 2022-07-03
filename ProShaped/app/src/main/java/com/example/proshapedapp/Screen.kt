package com.example.proshapedapp

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object MacrosScreen: Screen("macros")
    object CaloriesScreen: Screen("calories")
    object WorkoutScreen: Screen("workout")
    object GenderPicker: Screen ("genderPicker")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
