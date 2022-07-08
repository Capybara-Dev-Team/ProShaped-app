package com.example.proshapedapp

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object MacrosScreen: Screen("macros?id={id}")
    object CaloriesScreen: Screen("calories")
    object WorkoutScreen: Screen("workout")
    object GenderPicker: Screen ("genderPicker")
    object ChestScreen: Screen("chest_screen")
    object DeltoidsScreen: Screen("deltoids_screen")
    object TricepsScreen: Screen("triceps_screen")
    object BackScreen: Screen("back_screen")
    object BicepsScreen: Screen("biceps_screen")
    object LegsScreen: Screen("legs_screen")

    fun passId(id: Int): String{
        return "macros?id=$id"
    }
}
