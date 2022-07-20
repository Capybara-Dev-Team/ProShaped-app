package com.example.proshapedapp.workoutScreenPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proshapedapp.workoutScreenPackage.database.Graph
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutItem
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AddViewModel(
    private val workoutRepo: WorkoutRepository = Graph.workoutRepo,
    private val id: Long,
    private val name: String
) : ViewModel(){
    private val weightText = MutableStateFlow("")
    private val repsText = MutableStateFlow("")
    private val selectId = MutableStateFlow(-1L)

    private val _state = MutableStateFlow(AddViewState())
    val state: StateFlow<AddViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(weightText, repsText, selectId) { weight, reps, id ->
                AddViewState(weight, reps, id)
            }.collect {
                _state.value = it
            }
        }
    }

    init {
        viewModelScope.launch {
            workoutRepo.selectAll.collect { workout ->
                workout.find {
                    it.itemId == selectId.value
                }.also {
                    selectId.value = it?.itemId ?: -1
                    if (selectId.value != -1L){
                        weightText.value = it?.itemWeight ?: ""
                        repsText.value = it?.itemReps ?: ""
                    }
                }
            }
        }
    }

    fun onWeightChange(newText: String){
        weightText.value = newText
    }

    fun onRepsChange(newText: String){
        repsText.value = newText
    }

    fun insert(workoutItem: WorkoutItem) = viewModelScope.launch {
        workoutRepo.insertWorkout(workoutItem)
    }
}

data class AddViewState(
    val weight: String = "",
    val reps: String = "",
    val selectId: Long = -1L
)

@Suppress("UNCHECKED_CAST")
class AddViewModelFactory(private val id: Long, private val name: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(id = id, name = name) as T
        } else {
            throw IllegalArgumentException("unKnown view model class")
        }
    }
}