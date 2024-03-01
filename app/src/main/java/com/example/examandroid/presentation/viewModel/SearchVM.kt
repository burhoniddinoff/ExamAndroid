package com.example.examandroid.presentation.viewModel

import com.example.examandroid.data.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface SearchVM {
    val result: SharedFlow<List<WeatherData>>
    val error: Flow<String>

    fun textChange(text: String)
    fun onClickBack()
}