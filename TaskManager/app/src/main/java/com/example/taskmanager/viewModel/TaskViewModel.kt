package com.example.taskmanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.Task
import com.example.taskmanager.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

enum class SortType {
    NAME_ASC,
    NAME_DESC,
    DATE_ASC,
    DATE_DESC
}

enum class TaskTab {
    ALL,
    COMPLETED,
    UNCOMPLETED
}

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private var currentSort: SortType = SortType.NAME_ASC

    private val _filteredTasks = MutableStateFlow<List<Task>>(emptyList())
    val filteredTasks: StateFlow<List<Task>> = _filteredTasks.asStateFlow()

    private val _selectedTab = MutableStateFlow(TaskTab.ALL)
    val selectedTab: StateFlow<TaskTab> = _selectedTab.asStateFlow()

    init {
        // Collect Room's Flow into _tasks
        viewModelScope.launch {
            taskDao.getAllTasks().collectLatest { tasks ->
                _tasks.value = tasks
                updateFilteredTasks(tasks)
            }
        }
        viewModelScope.launch {
            _selectedTab.collectLatest {
                updateFilteredTasks(_tasks.value)
            }
        }
    }

    private fun updateFilteredTasks(tasks: List<Task>) {
        val sortedTasks = when (currentSort) {
            SortType.NAME_ASC -> tasks.sortedBy { it.name }
            SortType.NAME_DESC -> tasks.sortedByDescending { it.name }
            SortType.DATE_ASC -> tasks.sortedBy { it.dueDate }
            SortType.DATE_DESC -> tasks.sortedByDescending { it.dueDate }
        }
        _filteredTasks.value = when (_selectedTab.value) {
            TaskTab.ALL -> sortedTasks
            TaskTab.COMPLETED -> sortedTasks.filter { it.isCompleted }
            TaskTab.UNCOMPLETED -> sortedTasks.filter { !it.isCompleted }
        }
    }

    fun changeTab(tab: TaskTab) {
        _selectedTab.value = tab
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            taskDao.update(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }

    fun toggleSortByName() {
        currentSort = if (currentSort == SortType.NAME_ASC) {
            SortType.NAME_DESC
        } else {
            SortType.NAME_ASC
        }
        updateFilteredTasks(_tasks.value)
    }

    fun toggleSortByDate() {
        currentSort = if (currentSort == SortType.DATE_ASC) {
            SortType.DATE_DESC
        } else {
            SortType.DATE_ASC
        }
        updateFilteredTasks(_tasks.value)
    }
}