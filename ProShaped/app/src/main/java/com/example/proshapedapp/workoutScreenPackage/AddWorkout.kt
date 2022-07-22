package com.example.proshapedapp.workoutScreenPackage

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proshapedapp.Screen
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutItem

@Composable
fun AddWorkout(navController: NavController, selectedId: Long, name: String) {

    val viewModel = viewModel(
        AddViewModel::class.java,
        factory = AddViewModelFactory(selectedId, name)
    )
    val state by viewModel.state.collectAsState()

    var type by remember{
        mutableStateOf("")
    }
    if (name != null){
        type = name.substringAfter("/")
    }
    // !!! make sure the weight and the reps input is not negative


    //work on the database to be able to store the name of the workout (type), the weight and the reps
    //maybe add a feature where you would gain xp when you hit a certain weight for an ex and have a level bar

    //get the weight and reps from the textfield states and store them in db

    AddScreenComponent(
        weightText = state.weight,
        onWeightTextChange = { viewModel.onWeightChange(it) },
        repsText = state.reps,
        onRepsTextChange = { viewModel.onRepsChange(it) },
        navController = navController,
        onSaveWorkout = { viewModel.insert(it) },
        selectedId = state.selectId,
        name = type
    )

}

@Composable
fun AddScreenComponent(
    weightText: String,
    onWeightTextChange: (String) -> Unit,
    repsText: String,
    onRepsTextChange: (String) -> Unit,
    navController: NavController,
    onSaveWorkout: (WorkoutItem) -> Unit,
    selectedId: Long,
    name: String
) {
    val isWorkoutEdit = selectedId == -1L

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
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.size(16.dp))

        OutlinedTextField(
            value = weightTextFieldState,
            label = {
                Text(text = "Weight(Kgs)")
            },
            onValueChange = {
                onWeightTextChange(it)
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
                onRepsTextChange(it)
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
            val workout = if (isWorkoutEdit) WorkoutItem(selectedId, type, weightText, repsText)
            else WorkoutItem(selectedId, type, weightText, repsText)
            onSaveWorkout(workout)

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
            val text = if (isWorkoutEdit) "Save Workout" else "Update Workout"
            Text(text = text)
        }

    }
}