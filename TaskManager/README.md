# TaskManager

By **22110052 - Dang Huu Loc**

TaskManager is a mobile application built using Android's Jetpack Compose framework. It allows users to manage their tasks efficiently by adding, sorting, and filtering tasks. The app uses Room for local database storage and follows the MVVM (Model-View-ViewModel) architecture.

---

## Features

- **Add Tasks**: Create new tasks with a name, start date, and due date.
- **Task Completion**: Mark tasks as completed or uncompleted.
- **Sort Tasks**: Sort tasks by name or due date in ascending or descending order.
- **Filter Tasks**: View all tasks, only completed tasks, or only uncompleted tasks.
- **Delete Tasks**: Long-press on a task to delete it.
- **Local Storage**: Tasks are stored locally using Room database.
- **Modern UI**: Built with Jetpack Compose for a clean and responsive user interface.

---

## Screenshots

- **Main Screen**: Displays the list of tasks with sorting and filtering options.
- **Add Task Screen**: Allows users to create new tasks with details like name, start date, and due date.

---

## Architecture

The app follows the **MVVM architecture**:

- **Model**: Represents the data layer, including the `Task` entity and `TaskDao` for database operations.
- **ViewModel**: Manages UI-related data and business logic. The `TaskViewModel` handles task operations and state management.
- **View**: Composable UI components like `MainScreen`, `AddTaskScreen`, and `TaskItem`.

---

## Tech Stack

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Database**: Room
- **Dependency Injection**: Hilt (planned for future integration)
- **Build System**: Gradle (Kotlin DSL)
- **Testing**: JUnit for unit tests, AndroidX Test for instrumentation tests

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/HLoc26/Mobile-Programming-Homework.git
    ```
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Build and run the app on an emulator or physical device.

## Usage
1. Launch the app.
2. Add tasks by clicking the floating action button on the main screen.
3. Sort tasks using the sort buttons in the top bar.
4. Filter tasks using the tabs (All, Completed, Uncompleted).
5. Long-press a task to delete it.

## File Structure
 
- **data**: Contains the Task entity, TaskDao, and TaskDatabase.
- **viewModel**: Contains the TaskViewModel and TaskViewModelFactory.
- **ui**: Contains the composable UI components and screens.
- **theme**: Defines the app's theme, colors, and typography.

## Dependencies

The app uses the following dependencies:

- **Room**: For database operations
- **Jetpack Compose**: For UI
- **AndroidX libraries**: For lifecycle and navigation
- **Material3**: For modern UI components

## License
This project is licensed under the Apache License 2.0. See the LICENSE file for details.
---
Feel free to contribute to the project by submitting issues or pull requests!