package com.example.dessertclicker.model

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUIState(
    var revenue: Int = 0,
    var currentDessertIndex: Int = 0,
    var dessertsSold: Int = 0,
    var currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes var currentDessertImageId: Int = dessertList[currentDessertIndex].imageId,
)
