package com.example.proshapedapp.workoutScreenPackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proshapedapp.workoutScreenPackage.database.Graph
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutItem
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DisplayViewModel(private val workoutRepository: WorkoutRepository = Graph.workoutRepo): ViewModel() {
    private val _state = MutableStateFlow(DisplayViewState())
    val state: StateFlow<DisplayViewState>
        get() = _state

    val workoutList = workoutRepository.selectAll
    val selected = MutableStateFlow(_state.value.selected)

    init {
        viewModelScope.launch {
            combine(workoutList, selected){ workoutList: List<WorkoutItem>, selected: Boolean ->
                DisplayViewState(workoutList, selected)
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateWorkout(selected: Boolean, id: Long) = viewModelScope.launch {
        workoutRepository.updateWorkout(selected, id)
    }

    fun deleteWorkout(workoutItem: WorkoutItem) = viewModelScope.launch {
        workoutRepository.deleteWorkout(workoutItem)
    }

}

data class DisplayViewState(
    val workoutList: List<WorkoutItem> = emptyList(),
    val selected: Boolean = false
)