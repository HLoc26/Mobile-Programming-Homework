# Dice Roller App

A simple Android app built using Jetpack Compose that simulates rolling a six-sided die. The app displays a dice image and allows users to roll it by clicking a button.

## Features
- Displays a six-sided dice image.
- Button to roll the dice and generate a random number between 1 and 6.
- Uses Jetpack Compose for UI design.

## Getting Started
### Prerequisites
- Android Studio (latest version recommended)
- Kotlin and Jetpack Compose enabled in your project

### Installation
1. Clone this repository:
   ```sh
   git clone https://github.com/HLoc26/Mobile-Programming-Homework.git
   cd DiceRoller
   ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Code Overview
### `MainActivity.kt`
The entry point of the app, which sets up the Compose UI:
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}
```

### `DiceWithButtonAndImage` Composable
- Uses `remember` to hold the dice roll state.
- Displays an image corresponding to the dice roll result.
- Provides a button to roll the dice.
```kotlin
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(imageResource), contentDescription = "Dice Image")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(R.string.roll))
        }
    }
}
```

### `DiceRollerApp` Composable
Wraps the `DiceWithButtonAndImage` function and centers it on the screen.
```kotlin
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}
```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments
- Jetpack Compose documentation
- Android Developers community
