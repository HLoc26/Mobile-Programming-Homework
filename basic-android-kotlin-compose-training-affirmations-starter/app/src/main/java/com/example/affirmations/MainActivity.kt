/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalLayoutDirection
import com.example.affirmations.data.Datasource

// Main entry point of the app, responsible for initializing the Compose UI
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // Surface acts as the root container for the UI, filling the screen and using the theme's background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp() // Call the main app composable to render the UI
                }
            }
        }
    }
}

// Composable that defines the main screen layout, adjusting for system UI elements like status bars
@Composable
fun AffirmationsApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize() // Ensure the surface takes up the entire screen
            .statusBarsPadding() // Add padding to avoid overlap with the status bar
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection)
            ) // Apply safe drawing padding to respect system UI on left and right
    ) {
        // Load affirmations from Datasource and display them in a list
        AffirmationsList(
            affirmationList = Datasource().loadAffirmations()
        )
    }
}

// Composable that renders a single affirmation as a card with an image, text, and a button
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) { // Use Material Design Card to wrap the content
        Column { // Stack the image and text/button row vertically
            // Display the affirmation's image
            Image(
                painter = painterResource(affirmation.imageResourceId), // Load image from resources
                contentDescription = stringResource(affirmation.stringResourceId), // Accessibility description
                modifier = Modifier
                    .fillMaxWidth() // Make image span the card's width
                    .height(194.dp), // Fixed height for consistent card appearance
                contentScale = ContentScale.Crop // Crop the image to fit the dimensions
            )
            // Row to place text and button side by side with an 80:20 width ratio
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the row spans the card's width
                    .padding(16.dp) // Add padding around the row
            ) {
                // Text takes 80% of the available width
                Text(
                    text = LocalContext.current.getString(affirmation.stringResourceId), // Fetch text from string resources
                    modifier = Modifier
                        .weight(0.8f), // Allocate 80% of the width to the text
                    style = MaterialTheme.typography.headlineSmall, // Apply predefined typography style
                )
                // Button takes 20% of the available width with improved styling
                Button(
                    onClick = { /* Add button action here, e.g., log or navigate */ }, // Define button behavior
                    modifier = Modifier
                        .weight(0.2f) // Allocate 20% of the width to the button
                        .padding(start = 6.dp) // Add spacing between text and button
                        .height(36.dp), // Set a fixed height for better proportion
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding // Adjust padding for compactness
                ) {
                    Text(
                        text = "More", // Button label
                        style = MaterialTheme.typography.labelSmall, // Use smaller typography for better fit
                        maxLines = 1 // Restrict to single-line text
                    )
                }
            }
        }
    }
}

// Preview composable to visualize an AffirmationCard in Android Studio's preview pane
@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1)) // Sample affirmation for preview
}

// Composable that displays a scrollable list of affirmations
@Composable
fun AffirmationsList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    // LazyColumn is used for efficient rendering of potentially long lists
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation -> // Iterate over the affirmation list
            // Render each affirmation as a card with padding between items
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp) // Add spacing between cards
            )
        }
    }
}