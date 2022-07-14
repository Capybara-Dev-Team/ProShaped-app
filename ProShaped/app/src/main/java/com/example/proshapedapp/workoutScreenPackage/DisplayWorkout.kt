package com.example.proshapedapp.workoutScreenPackage

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proshapedapp.Screen
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutItem
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutViewModel


// !!! pass an id as argument to know what to display !!!
@Composable
fun DisplayWorkout(navController: NavController, name: String?) {
    var type by remember{
        mutableStateOf("")
    }
    if (name != null){
        type = name.substringAfter("/")
    }
    var id = 0
    //chest exercises
    if (type == "bbflat" || type == "bbincline" || type == "bbdecline" ||
            type == "dbflat" || type == "dbincline" || type == "dbdecline" ||
            type == "dips" || type == "flye" || type == "pushup" || type == "cablecrossover"){
        id = 0
    }
    //check also for the rest

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //implement lazycolumn to display every single item with the itemName name

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Button(
                onClick = {
                    if (id == 0){
                        navController.navigate(Screen.ChestScreen.route)
                    }else if (id == 1){
                        navController.navigate(Screen.DeltoidsScreen.route)
                    }else if (id == 2){
                        navController.navigate(Screen.TricepsScreen.route)
                    }else if (id == 3){
                        navController.navigate(Screen.BackScreen.route)
                    }else if (id == 4){
                        navController.navigate(Screen.BicepsScreen.route)
                    }else if (id == 5){
                        navController.navigate(Screen.LegsScreen.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.LightGray

                ),
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Blue)),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(75.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent
                            ),
                            startX = 150f
                        )
                    )
            ) {
                Text(text = "Go back", fontSize = 8.sp)
            }
        }

        //just to see that the right argument is passed
        Text(text = type)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.DisplayScreen.withArgs(type))
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.LightGray

                ),
                modifier = Modifier
                    .border(
                        width = 5.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Cyan, Color.Blue)),
                        shape = RoundedCornerShape(1000.dp)
                    )
                    .width(50.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent
                            ),
                            startX = 150f
                        )
                    )
            ) {
                Text(text = "+", fontSize = 18.sp)
            }
        }
    }
}