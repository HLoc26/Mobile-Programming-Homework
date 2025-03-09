package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import com.example.dessertclicker.model.DualDessertUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the DessertClicker app that manages UI state and business logic for two desserts
 */
class DessertViewModel : ViewModel() {

    // Private mutable state flow that stores the current UI state
    private val _uiState = MutableStateFlow(DualDessertUIState())

    // Public immutable state flow that the UI can observe
    val uiState: StateFlow<DualDessertUIState> = _uiState.asStateFlow()

    /**
     * Handle the first dessert click event
     */
    fun onFirstDessertClicked() {
        _uiState.update { currentState ->
            // Calculate new values
            val newRevenue = currentState.revenue + currentState.firstDessertPrice
            val newDessertsSold = currentState.dessertsSold + 1

            // Determine the next dessert to show
            val dessertToShow = determineDessertToShow(Datasource.dessertList, newDessertsSold)

            // Return a new copy of the state with updated values
            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                firstDessertImageId = dessertToShow.imageId,
                firstDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Handle the second dessert click event
     */
    fun onSecondDessertClicked() {
        _uiState.update { currentState ->
            // Calculate new values
            val newRevenue = currentState.revenue + currentState.secondDessertPrice
            val newDessertsSold = currentState.dessertsSold + 1

            // Determine the next dessert to show
            // Using a different calculation for the second dessert to have variety
            val secondDessertSoldThreshold = currentState.dessertsSold + 3
            val dessertToShow = determineDessertToShow(Datasource.dessertList, secondDessertSoldThreshold)

            // Return a new copy of the state with updated values
            currentState.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                secondDessertImageId = dessertToShow.imageId,
                secondDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Determine which dessert to show based on the number of desserts sold
     */
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}