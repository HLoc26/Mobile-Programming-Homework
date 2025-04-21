package com.example.taskmanager.ui.components

import android.app.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(label: String, selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit){
    val context = LocalContext.current;
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    TextButton(onClick = {
        val datePicker = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            selectedDate.year,
            selectedDate.monthValue - 1,
            selectedDate.dayOfMonth
        )
        datePicker.show()
    }) {
        Text("$label: ${selectedDate.format(dateFormatter)}")    }
}