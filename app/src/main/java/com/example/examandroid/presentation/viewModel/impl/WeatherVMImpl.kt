package com.example.examandroid.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examandroid.data.model.WeatherData
import com.example.examandroid.domain.AppRepository
import com.example.examandroid.navigation.AppNavigator
import com.example.examandroid.presentation.screen.WeatherScreenDirections
import com.example.examandroid.presentation.viewModel.WeatherVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherVMImpl @Inject constructor(
    private val repository: AppRepository,
    private val appNavigator: AppNavigator,
) : ViewModel(), WeatherVM {


    override val success = MutableSharedFlow<List<WeatherData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun loadWeather() {

        repository.getWeather().onEach {
            it.onSuccess { data ->
                success.tryEmit(data)
            }
            it.onFailure { thr ->
                error.tryEmit(thr.toString())
            }
        }.launchIn(viewModelScope)

    }

    override fun infoScreen(it: WeatherData) {
        viewModelScope.launch {
            appNavigator.navigateTo(WeatherScreenDirections.actionWeatherScreenToInfoScreen(it))
        }
    }


}