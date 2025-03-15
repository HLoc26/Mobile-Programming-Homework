package com.example.alarmapp
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var setAlarmButton: Button
    private lateinit var alarmTimeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init UI components
        timePicker = findViewById(R.id.timePicker)
        setAlarmButton = findViewById(R.id.setAlarmButton)
        alarmTimeTextView = findViewById(R.id.alarmTimeTextView)

        // Set 24-hour format
        timePicker.setIs24HourView(true)

        // Update the text when the user selects a new time
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val timeString = String.format("%02d:%02d", hourOfDay, minute)
            alarmTimeTextView.text = "Selected time: $timeString"
        }

        // Setup button click listener
        setAlarmButton.setOnClickListener {
            setAlarm()
        }
    }


    private fun setAlarm() {
        // Get selected time from TimePicker
        @Suppress("DEPRECATION")
        val hour = timePicker.currentHour
        @Suppress("DEPRECATION")
        val minute = timePicker.currentMinute

        val timeString = String.format("%02d:%02d", hour, minute)
        alarmTimeTextView.text = "Selected time: $timeString"

        // Create the intent with all required extras
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minute)
            putExtra(AlarmClock.EXTRA_MESSAGE, "Wake up for class!")
            // True: create the alarm without showing UI
            // False: create alarm and show alarm app
             putExtra(AlarmClock.EXTRA_SKIP_UI, false)
        }

        try {
            // Log for debugging
            Log.d("AlarmApp", "Attempting to set alarm for $hour:$minute")

            // Start the activity
            startActivity(intent)
            Toast.makeText(this, "Alarm set for $timeString", Toast.LENGTH_SHORT).show()
        } catch (e: ActivityNotFoundException) {
            Log.e("AlarmApp", "Failed to set alarm: ${e.message}")
            Toast.makeText(this, "No app can set alarms on this device", Toast.LENGTH_LONG).show()
        }
    }
}

