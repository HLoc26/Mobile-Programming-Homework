# Dang Huu Loc - 22110052

# Gallery App

A modern Android photo gallery application built with Jetpack Compose and Material Design 3.

## Features

-   Grid view of photos with smooth animations
-   Full-screen photo viewer with gestures
    -   Pinch to zoom
    -   Double-tap to zoom
    -   Swipe navigation between photos
-   Photo management
    -   Add photos from camera
    -   Import from gallery
    -   Delete photos
    -   Mark photos as favorites
-   Dark/Light theme support
-   Room database for persistent storage
-   Material You design language

## Tech Stack

-   **Language**: Kotlin
-   **UI Framework**: Jetpack Compose
-   **Architecture**: MVVM
-   **Dependencies**:
    -   Jetpack Compose UI (1.7.0)
    -   Material Design 3 (1.3.0)
    -   Room Database (2.6.1)
    -   Coil for image loading (2.6.0)
    -   Navigation Compose (2.8.0)
    -   Kotlin Coroutines (1.8.1)

## Requirements

-   Android Studio Electric Eel or newer
-   Minimum SDK: 21 (Android 5.0)
-   Target SDK: 35
-   JDK 11

## Setup

1. Clone the repository

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

## Architecture

The app follows MVVM (Model-View-ViewModel) architecture pattern:

-   **Model**: Room database with Photo entities
-   **View**: Compose UI components in the `ui.views` package
-   **ViewModel**: GalleryViewModel managing UI state and business logic

## License

[Add your license here]
