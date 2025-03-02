Affirmations App - Starter Code
================================

Starter code for the Android Basics with Compose: Affirmations app.


Introduction
------------
The Affirmations app contains a scrollable list of 10 cards.


Pre-requisites
--------------
* Familiarity with Lists in Kotlin
* Experience building layouts with Jetpack Compose
* Experience running apps on a device or emulator


Getting Started
---------------
1. Install Android Studio, if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

All photos by Romain Guy. All photos are licensed under CC0 https://creativecommons.org/share-your-work/public-domain/cc0/

# Affirmations App

The **Affirmations App** is a simple Android application built with Jetpack Compose. It displays a scrollable list of affirmations, each accompanied by an inspiring image and motivational text. This project showcases modern Android development techniques using Compose for UI design and Material Design components for styling.

## Features

- **Scrollable List**: View a list of affirmations efficiently rendered with `LazyColumn`.
- **Affirmation Cards**: Each affirmation is presented in a card with an image and text.
- **Modern UI**: Built with Jetpack Compose and styled with Material Design principles.

## Screenshots

_(Screenshots of the app can be added here to showcase the UI.)_

## Setup Instructions

To run the Affirmations App on your local machine, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/affirmations-app.git
   ```
2. **Open in Android Studio**: Launch Android Studio and select "Open an existing project," then navigate to the cloned directory.
3. **Build the Project**: Let Gradle sync and build the project automatically (or manually trigger it via `Build > Make Project`).
4. **Run the App**: Connect an Android device or start an emulator, then click `Run > Run 'app'` to launch the app.

**Requirements**:
- Android Studio (latest stable version recommended)
- Minimum API level: Check the `build.gradle` file (assumed API 21 or higher unless specified)
- Jetpack Compose support enabled

## Dependencies

- **Jetpack Compose**: For building the UI declaratively.
- **Material Design Components**: For styling the cards and typography.
- _(Additional dependencies may be listed in the `build.gradle` file if present.)_

## Usage

Once the app is running:
- Scroll through the list to view affirmations.
- Each card displays an image and a motivational message sourced from the app's resources.

To add or modify affirmations:
- Update the `Datasource` class in `com.example.affirmations.data` with new `Affirmation` objects (containing string and image resource IDs).

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Description of changes"`).
4. Push to your branch (`git push origin feature-name`).
5. Open a pull request with a detailed description of your changes.

## License

This project is licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) file for more details:

```
Copyright (C) 2023 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```