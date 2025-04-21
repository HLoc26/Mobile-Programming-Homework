package com.example.taskmanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity (tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="start_date")
    val startDate: LocalDate,

    @ColumnInfo(name="due_date")
    val dueDate: LocalDate,

    @ColumnInfo(name="is_completed")
    val isCompleted: Boolean = false
)
