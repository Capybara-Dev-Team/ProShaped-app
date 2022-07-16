package com.example.proshapedapp.workoutScreenPackage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proshapedapp.Screen
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutDatabaseDao
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutRepository
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutViewModel
import java.lang.NumberFormatException

@Composable
fun AddWorkout(navController: NavController, name: String?) {
    lateinit var mWorkoutViewModel: WorkoutViewModel

    var type by remember{
        mutableStateOf("")
    }
    if (name != null){
        type = name.substringAfter("/")
    }
    var weightTextFieldState by remember{
        mutableStateOf("")
    }
    var repsTextFieldState by remember{
        mutableStateOf("")
    }
    var weight: Float
    var reps: Int
    // !!! make sure the weight and the reps input is not negative

    /*mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)*/

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.TopStart
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        OutlinedTextField(
            value = weightTextFieldState,
            label = {
                Text(text = "Weight(Kgs)")
            },
            onValueChange = {
                weightTextFieldState = it
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.75f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.size(32.dp))

        OutlinedTextField(
            value = repsTextFieldState,
            label = {
                Text(text = "Reps")
            },
            onValueChange = {
                repsTextFieldState = it
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.75f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.size(32.dp))

        Button(onClick = {
            //add to the database ...
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
                    shape = RoundedCornerShape(15.dp)
                )
                .width(80.dp)
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
            Text(text = "Add", fontSize = 16.sp)
        }
    }

    //get the weight and reps from the textfield states and store them in db
}