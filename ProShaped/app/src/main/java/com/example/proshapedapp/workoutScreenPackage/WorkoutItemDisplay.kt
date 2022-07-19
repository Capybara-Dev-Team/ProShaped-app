package com.example.proshapedapp.workoutScreenPackage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proshapedapp.workoutScreenPackage.database.WorkoutItem

@Composable
fun WorkoutItemDisplay(
    workoutItem: WorkoutItem,
    onChecked: (WorkoutItem) -> Unit,
    onDelete: (WorkoutItem) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Checkbox(checked = workoutItem.isDone, onCheckedChange = { onChecked(workoutItem) })

            Spacer(modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = workoutItem.itemReps, style = MaterialTheme.typography.subtitle2)

                Spacer(modifier = Modifier.size(4.dp))

                Text(text = "x", style = MaterialTheme.typography.subtitle2)

                Spacer(modifier = Modifier.size(4.dp))

                Text(text = workoutItem.itemWeight + "kgs", style = MaterialTheme.typography.subtitle2)
            }

            Spacer(modifier = Modifier.size(16.dp))

            IconButton(onClick = { onDelete(workoutItem) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
        }
    }    
}