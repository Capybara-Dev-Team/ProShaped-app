package com.example.proshapedapp.workoutScreenPackage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proshapedapp.Screen


// !!! pass an id as argument to know what to display !!!
@Composable
fun DisplayWorkout(navController: NavController, name: String?) {
    val viewModel = viewModel(DisplayViewModel::class.java)
    val state by viewModel.state.collectAsState()
    var type by remember{
        mutableStateOf("")
    }
    if (name != null){
        type = name.substringAfter("/")
    }
    var id = 0
    var selectedId by remember{
        mutableStateOf(0)
    }

    if (type == "bbflat" || type == "bbincline" || type == "bbdecline" ||
            type == "dbflat" || type == "dbincline" || type == "dbdecline" ||
            type == "dips" || type == "flye" || type == "pushup" || type == "cablecrossover"){
        id = 0
    }else if (type == "bboverheadpress" || type == "dboverheadpress" ||
            type == "landminepress" || type == "lateralraise" ||
            type == "frontraise" || type == "reardeltraise" ||
            type == "facepull" || type == "reversemachineflye"){
        id = 1
    }else if (type == "closegripbenchpress" || type == "skullcrushers" ||
            type == "pushdown" || type == "ropepushaway" || type == "diamondpushup" ||
            type == "dbkickback" || type == "uprightdips" || type == "machinedip"){
        id = 2
    }else if (type == "backhyperextension" || type == "dbrow" ||
            type == "straightarmpushdown" || type == "seatedcablerow" ||
            type == "latpulldown" || type == "pullup" ||
            type == "bbrow" || type == "deadlift"){
        id = 3
    }else if (type == "waiterscurl" || type == "reversecurl" || type == "cablecurl" ||
            type == "concentrationcurl" || type == "dbcurl" || type == "hammercurl" ||
            type == "preachercurl" || type == "chinup" || type == "bbcurl"){
        id = 4
    }else if (type == "seatedcalfraise" || type == "standingcalfraise" || type == "legextensions" ||
            type == "legcurl" || type == "bulgariansplitsquat" || type == "gobletsquat" ||
            type == "hipthrust" || type == "lunges" || type == "goodmornings" ||
            type == "rdl" || type == "legpress" || type == "hacksquat" ||
            type == "frontsquat" || type == "backsquat"){
        id = 5
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        LazyColumn {
            items(state.workoutList) { workout->
                if (workout.itemName == name){
                    WorkoutItemDisplay(
                        workoutItem = workout,
                        onChecked = { viewModel.updateWorkout(workout.isDone, workout.itemId) },
                        onDelete = { viewModel.deleteWorkout(it) }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/{${selectedId}}" + "/{$type}")
                    selectedId++
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