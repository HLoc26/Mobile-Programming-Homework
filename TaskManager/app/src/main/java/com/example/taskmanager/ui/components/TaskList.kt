package com.example.taskmanager.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanager.data.Task

@Composable
fun TaskList(tasks: List<Task>, onToggleCompletion: (Task) -> Unit, onDeleteTask: (Task) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(tasks) { task ->
            TaskItem(
                task,
                onToggleCompletion = { onToggleCompletion(task) },
                onDeleteTask = { onDeleteTask(task) }
            )
        }
    }
}
