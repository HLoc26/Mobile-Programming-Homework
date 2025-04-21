import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.MainActivity
import com.example.taskmanager.data.TaskDatabase

import com.example.taskmanager.ui.view.AddTaskScreen
import com.example.taskmanager.ui.view.MainScreen
import com.example.taskmanager.viewModel.TaskViewModel
import com.example.taskmanager.viewModel.TaskViewModelFactory

@Composable
fun TaskManager(applicationContext: Context, mainActivity: MainActivity) {
    val navController = rememberNavController()
    // Initialize database and DAO
    val taskDao = TaskDatabase.getDatabase(applicationContext).taskDao()
    // Create ViewModel
    val viewModel = ViewModelProvider(
        mainActivity,
        TaskViewModelFactory(taskDao)
    )[TaskViewModel::class.java]
    NavHost(navController, startDestination = "main"){
        composable("main"){
            MainScreen(viewModel, navController)
        }
        composable("addTask"){
            AddTaskScreen(viewModel, navController)
        }
    }
}