package com.example.taskmanager.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskmanager.ui.components.TaskList
import com.example.taskmanager.viewModel.TaskTab
import com.example.taskmanager.viewModel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: TaskViewModel, navController: NavController) {
    val tasks by viewModel.filteredTasks.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Manager") },
                actions = {
                    IconButton(onClick = { viewModel.toggleSortByName() }) {
                        Icon(Icons.Default.SortByAlpha, contentDescription = "Sort by name")
                    }
                    IconButton(onClick = { viewModel.toggleSortByDate() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Sort by date")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTask") }) {
                Icon(Icons.Default.Add, contentDescription = "Add task")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(selectedTabIndex = selectedTab.ordinal) {
                TaskTab.entries.forEach { tab ->
                    Tab(
                        text = {Text(tab.name)},
                        selected = selectedTab == tab,
                        onClick = {viewModel.changeTab(tab)}
                    )
                }
            }
            TaskList(
                tasks = tasks,
                onToggleCompletion = {task -> viewModel.toggleTaskCompletion(task)},
                onDeleteTask = {task -> viewModel.deleteTask(task)},
            )
        }
    }
}