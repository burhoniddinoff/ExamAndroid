package com.example.examandroid.domain

import com.example.examandroid.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getWeather(): Flow<Result<List<WeatherData>>>
    fun getSearchWeather(name: String): Flow<Result<List<WeatherData>>>

}