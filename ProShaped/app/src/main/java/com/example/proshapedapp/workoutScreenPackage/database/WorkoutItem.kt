package com.example.proshapedapp.workoutScreenPackage.database

import androidx.room.*

@Entity(tableName = "workout_list")
data class WorkoutItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "item_weight")
    val itemWeight: Float,

    @ColumnInfo(name = "item_reps")
    val itemReps: Int,

    @ColumnInfo(name = "is_completed")
    var isDone: Boolean = false
)