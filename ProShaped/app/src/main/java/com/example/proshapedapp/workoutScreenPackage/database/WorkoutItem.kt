package com.example.proshapedapp.workoutScreenPackage.database

import androidx.room.*

@Entity(tableName = "workout_list")
data class WorkoutItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "item_weight")
    val itemWeight: String,

    @ColumnInfo(name = "item_reps")
    val itemReps: String,

    @ColumnInfo(name = "is_completed")
    var isDone: Boolean = false
)