package com.example.examandroid.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examandroid.data.model.WeatherData
import com.example.examandroid.domain.AppRepository
import com.example.examandroid.myLog
import com.example.examandroid.navigation.AppNavigator
import com.example.examandroid.presentation.viewModel.SearchVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVMImpl @Inject constructor(
    private val repository: AppRepository,
    private val appNavigator: AppNavigator,
) : ViewModel(), SearchVM {
    override val result = MutableSharedFlow<List<WeatherData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val error = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun textChange(text: String) {
        repository.getSearchWeather(text)
            .onEach {
                it.onSuccess { list ->
                    "listsize Vm ${list.size}".myLog()
                    result.tryEmit(list)
                }.onFailure { thr ->
                    error.tryEmit(thr.toString())
                }
            }.launchIn(viewModelScope)
    }


    override fun onClickBack() {
        viewModelScope.launch {
            appNavigator.popBackStack()
        }
    }


}