package com.example.proshapedapp.workoutScreenPackage.database

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

//create input field and then add to do in BBFlat etc

fun insertWorkoutInDB(workout: String, weight: Float, reps: Int,mWorkoutViewModel: WorkoutViewModel){
    if (workout.isNotEmpty()){
        val workoutItem = WorkoutItem(
            itemName = workout,
            itemWeight = weight,
            itemReps = reps,
            isDone = false
        )

        mWorkoutViewModel.addWorkout(workoutItem)
    }
}

class InputViewModel: ViewModel(){
    private val _workout: MutableLiveData<String> = MutableLiveData("")
    val workout: LiveData<String> = _workout

    private val _weight: MutableLiveData<Float> = MutableLiveData(0F)
    val weight: LiveData<Float> = _weight

    private val _reps: MutableLiveData<Int> = MutableLiveData(0)
    val reps: LiveData<Int> = _reps

    fun onInputChange(newName: String, newWeight: Float, newReps: Int){
        _workout.value = newName
        _weight.value = newWeight
        _reps.value = newReps
        //here probably add weight and reps
    }
}