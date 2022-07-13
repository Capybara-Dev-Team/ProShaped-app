package com.example.proshapedapp.workoutScreenPackage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.lang.NumberFormatException

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddWorkout(navController: NavController, name: String?) {
    var type by remember{
        mutableStateOf("")
    }
    if (name != null){
        type = name
    }
    var weightTextFieldState by remember{
        mutableStateOf("")
    }
    var repsTextFieldState by remember{
        mutableStateOf("")
    }
    var weight: Float
    var reps: Int

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
            value = weightTextFieldState,
            label = {
                Text(text = "Reps")
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

        weight = weightTextFieldState.toFloat()
        reps = repsTextFieldState.toInt()
        //use these to update database and name

        Spacer(modifier = Modifier.size(32.dp))


    }
}