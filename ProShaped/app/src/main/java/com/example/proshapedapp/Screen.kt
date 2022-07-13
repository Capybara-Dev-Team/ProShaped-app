package com.example.proshapedapp

const val MACROS_ARGUMENT_KEY = "id"

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object MacrosScreen: Screen("macros")
    object CaloriesScreen: Screen("calories")
    object WorkoutScreen: Screen("workout")
    object ChestScreen: Screen("chest_screen")
    object DeltoidsScreen: Screen("deltoids_screen")
    object TricepsScreen: Screen("triceps_screen")
    object BackScreen: Screen("back_screen")
    object BicepsScreen: Screen("biceps_screen")
    object LegsScreen: Screen("legs_screen")
    object AddScreen: Screen("add_screen?id={name2}")
    object DisplayScreen: Screen("display_screen?id={name}")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
