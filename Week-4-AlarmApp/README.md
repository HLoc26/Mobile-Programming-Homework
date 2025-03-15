# Alarm App

## Overview
This is a simple Android application that allows users to set an alarm using the device's default alarm clock. The app provides a user-friendly interface with a `TimePicker` to select the desired time and a button to set the alarm.

## Features
- Select alarm time using a `TimePicker`
- Display selected time on the screen
- Set an alarm with a predefined message
- Uses Android's built-in alarm system

## Technologies Used
- **Language:** Kotlin
- **Android Components:**
  - `TimePicker`
  - `Button`
  - `TextView`
  - `Intent` (AlarmClock API)
  - `Toast` notifications
  - `Log` for debugging

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/HLoc26/Mobile-Programming-Homework
   ```
2. Open the  "Week-4-AlarmApp" project in **Android Studio**.
3. Build and run the application on an emulator or a real Android device.

## Usage
1. Open the app.
2. Select the desired alarm time using the `TimePicker`.
3. Click on the **Set Alarm** button.
4. The selected time will be displayed on the screen.
5. The device's alarm clock will open to confirm the alarm.

## Code Explanation
- `MainActivity` initializes the UI components and handles user interactions.
- The `setAlarm()` function retrieves the selected time and creates an intent to set an alarm.
- The intent uses `AlarmClock.ACTION_SET_ALARM` to interface with the device’s alarm system.
- `try-catch` ensures that an appropriate message is displayed if no alarm-setting app is available.