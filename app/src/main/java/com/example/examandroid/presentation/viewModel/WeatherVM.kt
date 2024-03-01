package com.example.examandroid.presentation.viewModel

import com.example.examandroid.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherVM {
    val success: Flow<List<WeatherData>>
    val error: Flow<String>
    fun loadWeather()

    fun infoScreen(it: WeatherData)
}