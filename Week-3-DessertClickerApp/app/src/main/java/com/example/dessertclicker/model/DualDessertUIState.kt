package com.example.dessertclicker.model

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

/**
 * Data class that represents the UI state with two desserts
 */
data class DualDessertUIState(
    var revenue: Int = 0,
    var dessertsSold: Int = 0,

    // First dessert properties
    var firstDessertPrice: Int = dessertList[0].price,
    @DrawableRes var firstDessertImageId: Int = dessertList[0].imageId,

    // Second dessert properties - initialize with a different dessert
    var secondDessertPrice: Int = dessertList[min(1, dessertList.size - 1)].price,
    @DrawableRes var secondDessertImageId: Int = dessertList[min(1, dessertList.size - 1)].imageId
)

private fun min(a: Int, b: Int): Int = if (a <= b) a else b